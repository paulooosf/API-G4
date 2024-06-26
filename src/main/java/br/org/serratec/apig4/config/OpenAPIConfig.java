package br.org.serratec.apig4.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

	@Value("${dominio.openapi.dev-url}")
	private String devUrl;

	@Value("${dominio.openapi.prod-url}")
	private String prodUrl;

	@Bean
	OpenAPI myOpenApi() {
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("URL do servidor de desenvolvimento");
		Server prodServer = new Server();
		prodServer.setUrl(prodUrl);
		prodServer.setDescription("URL do servidor de produção");

		Contact contact = new Contact();
		contact.setEmail("contato@grupo4.com.br");
		contact.setName("GitHub do projeto");
		contact.setUrl("https://github.com/paulooosf/API-G4");

		License apacheLicense = new License().name("Apache License").url("https://www.apache.org/license/LICENSE-2.0");

		Info info = new Info().title("API simples de Rede Social").version("1.0").contact(contact)
				.description("API desenvolvida durante a disciplina de Desenvolvimento de API"
						+ " Restful da Residência em TIC/Software do Serratec.")
				.termsOfService("https://www.grupo4.com.br/termos").license(apacheLicense);

		return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
	}

}