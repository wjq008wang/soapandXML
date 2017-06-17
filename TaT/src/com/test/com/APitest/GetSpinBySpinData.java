package com.test.com.APitest;

import java.io.UnsupportedEncodingException;

public class GetSpinBySpinData {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String loginName = "username";
		String loginPassword = "password";

		// var req = new {LastRowId = XXXXXXX};
		String method = "GetSpinBySpinData";
		String apiBaseUrl = "https://tegapi.totalegame.net/";

		String hashPincode = loginName + ":" + loginPassword;
		byte[] jj = hashPincode.getBytes("UTF-8");

		byte[] imgBytes = org.apache.ws.commons.util.Base64.decode();

		// byte[] jj =System.Text.Encoding.default.GetBytes(hashPincode);
		// String bytes = Encoding.UTF8.GetBytes(hashPincode);
		// var authValue = Convert.ToBase64String(bytes);
		// var authHeader = new AuthenticationHeaderValue("Basic", authValue);
		//
		//
		// using(var client = new HttpClient())
		// {
		// client.BaseAddress = new Uri(apiBaseUrl);
		// client.DefaultRequestHeaders.Accept.Clear();
		// //Add Content-Type header
		// client.DefaultRequestHeaders.Accept.Add(new
		// MediaTypeWithQualityHeaderValue("application/json"));
		// // Assign the authentication headers
		// client.DefaultRequestHeaders.Authorization = authHeader;
		//
		// HttpResponseMessage response = client.PostAsJsonAsync(method,
		// req).Result;
		// string responseContent = string.Empty;
		// if(response.IsSuccessStatusCode)
		// {
		// Task<string> responseBodyAsText =
		// response.Content.ReadAsStringAsync();
		// responseContent =responseBodyAsText.Result;
		// }
		//
		// Console.Out.WriteLine("responseContent = {0}", responseContent);

	}

}
