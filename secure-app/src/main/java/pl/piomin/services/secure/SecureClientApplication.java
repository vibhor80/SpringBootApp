package pl.piomin.services.secure;

import java.security.NoSuchAlgorithmException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;

@SpringBootApplication
public class SecureClientApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SecureClientApplication.class).run(args);
	}

	@Bean
	public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
		DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
		System.setProperty("javax.net.ssl.keyStore", "src/main/resources/MyClient.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "Modern123$");
		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/MyClient.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "Modern123$");
		EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
		builder.withClientName("client");
		builder.withSystemSSLConfiguration();
		builder.withMaxTotalConnections(10);
		builder.withMaxConnectionsPerHost(10);
		args.setEurekaJerseyClient(builder.build());
		return args;
	}

}
