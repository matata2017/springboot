package springboot;

import com.alibaba.fastjson.JSON;
import com.xxy.elasticsearch.indexes.People;
import com.xxy.elasticsearch.service.elasticsearchService;
import org.assertj.core.util.DateUtil;
import org.elasticsearch.action.index.IndexRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;

import static com.xxy.elasticsearch.service.impl.elasticsearchServiceImpl.generateNewsRequest;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsApplicationTests.class)
@AutoConfigureTestEntityManager
@DataJpaTest
@EnableAutoConfiguration
@AutoConfigureTestDatabase(replace = NONE)
@ComponentScan(value = {"com.xxy.**"})
@EnableJpaRepositories({"com.xxy.**"})
@EntityScan(basePackages = {"com.xxy.**"})
public class EsApplicationTests {
    @Autowired
    private elasticsearchService service;
    @Test
    public void test(){
     //   service.add();
     //   service.updateDoc("jo4lQGQBJS_-AxPE_yHq");
        People p1 = new People("sad", DateUtil.parse("2018-01-27"), 22);
        String source1 = JSON.toJSONString(p1);
        People p2 = new People("hahah", DateUtil.parse("2018-06-27"), 66);
        String source2 = JSON.toJSONString(p2);
        People p3 = new People("大事", DateUtil.parse("2018-05-27"), 22);
        String source3 = JSON.toJSONString(p3);
        List<IndexRequest> requests = new ArrayList<>();
        requests.add(generateNewsRequest(source1));
        requests.add(generateNewsRequest(source2));
        requests.add(generateNewsRequest(source3));
       // service.addBatch(requests);
       // service.query();
        service.delete("jo4lQGQBJS_-AxPE_yHq");
    }

}
