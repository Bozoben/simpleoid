package fr.ben.openid.simpleserver;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URLEncoder;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SimpleserverApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void authorizeShouldRedirectWithCode() throws Exception {
		String url = String.format("/authorize?redirect_url=%s", URLEncoder.encode("http://localhost:7777?foo=bar", "utf-8"));

		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("http://localhost:7777?foo=bar&code=*"));

		// A simpler url
		url = String.format("/authorize?redirect_url=%s", URLEncoder.encode("http://localhost:7777", "utf-8"));
		this.mockMvc.perform(get(url))
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrlPattern("http://localhost:7777?code=*"));

	}

	@Test
	public void shouldReturnToken() throws Exception {
		this.mockMvc.perform(get("/token"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("To be implemented")));
	}

	@Test
	public void shouldReturnUserInfo() throws Exception {
		this.mockMvc.perform(get("/userinfo"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("To be implemented")));
	}

}
