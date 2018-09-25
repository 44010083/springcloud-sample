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
 * @since <pre>���� 18, 2018</pre>
 */

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = DemoApplication.class)//Application������
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
        //������,��Ŀ��������Ч
        // MockMvcBuilders.standaloneSetup(new ProductController()).build();
        //��Ŀ��������Ч
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        //���ýӿڣ�������ӵ��û�����
        RequestBuilder request = MockMvcRequestBuilders.get("/hello")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JSON.toJSONString(null));

        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        status = mvcResult.getResponse().getStatus();
        content = mvcResult.getResponse().getContentAsString();
    }

    @Test
    public void testSayHelloStatus() throws Exception {
        Assert.assertTrue("����" + status + ";������:" + qStatus, status == qStatus);
    }

    @Test
    public void testSayHelloContent() throws Exception {
        Assert.assertTrue("����:" + content + ";������:" + qContent, content.equalsIgnoreCase(qContent));
    }


} 
