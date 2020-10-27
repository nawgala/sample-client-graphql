package com.hms.ssoapi.client.sampleclientgraphql;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.hms.client.generated.mutations.GetApiAccessTokenMutation;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class SampleClientGraphqlApplication {
	private static final String BASE_URL = "http://127.0.0.1:8080/graphql";


	public static void main(String[] args) {
		GetApiAccessTokenMutation getToken = GetApiAccessTokenMutation.builder()
				.clientId("client")
				.secret("secret").build();


		OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

		ApolloClient client = ApolloClient.builder()
				.serverUrl(BASE_URL)
				.okHttpClient(okHttpClient)
				.build();

		client.mutate(getToken).enqueue(new ApolloCall.Callback<GetApiAccessTokenMutation.Data>() {
			@Override
			public void onResponse(@NotNull Response<GetApiAccessTokenMutation.Data> response) {
				final GetApiAccessTokenMutation.GetApiAccessToken token = response.data().getApiAccessToken();
				System.out.println("token: " + token.access_token());
				System.out.println("expireIn: " + token.expires_in());
			}

			@Override
			public void onFailure(@NotNull ApolloException e) {
				//handle failure
			}
		});
	}

}
