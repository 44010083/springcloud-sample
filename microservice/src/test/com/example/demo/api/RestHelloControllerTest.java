package test.com.example.demo.api;

import com.alibaba.fastjson.JSON;
import com.example.demo.DemoApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * RestHelloController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>七月 18, 2018</pre>
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = DemoApplication.class)//Application启动类
public class RestHelloControllerTest {
    @Autowired
    public WebApplicationContext context;
    public MockMvc mockMvc;
    int status = -1;
    int qStatus = 200;
    String content = "";
    String qContent = "hello";

    @Before
    public void before() throws Exception {
        //单个类,项目拦截器无效
        // MockMvcBuilders.standaloneSetup(new ProductController()).build();
        //项目拦截器有效
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        //调用接口，传入添加的用户参数
        RequestBuilder request = MockMvcRequestBuilders.get("/hello")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(null));

        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        status = mvcResult.getResponse().getStatus();
        content = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void testSayHelloStatus() throws Exception {
        Assert.assertTrue("返回" + status + ";期望是:" + qStatus, status == qStatus);
    }

    @Test
    public void testSayHelloContent() throws Exception {
        Assert.assertTrue("返回:" + content + ";期望是:" + qContent, content.equalsIgnoreCase(qContent));
    }


} 
