package main;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class GitHubApiExercise {
	
	public static void main(String[] args) {
        String apiUrl = "https://api.github.com/repos/marianabborda/Java_y_SO";
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);

                ObjectMapper objectMapper = new ObjectMapper();
                RepositoryInfo repositoryInfo = objectMapper.readValue(result, RepositoryInfo.class);

                // Imprime la información del repositorio
                System.out.println("Nombre: " + repositoryInfo.getName());
                System.out.println("Descripción: " + repositoryInfo.getDescription());
                System.out.println("URL: " + repositoryInfo.getHtmlUrl());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RepositoryInfo {
        private String name;
        private String description;

        @JsonProperty("html_url")
        private String htmlUrl;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getHtmlUrl() {
            return htmlUrl;
        }

        public void setHtmlUrl(String htmlUrl) {
            this.htmlUrl = htmlUrl;
        }
    }
}	