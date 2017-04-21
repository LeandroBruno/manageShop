package com.javaunittest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogmanagementApplicationTests {

	@Test
	public void contextLoads() {
		Assert.isTrue(true, "this is a simple test");
	}

}
