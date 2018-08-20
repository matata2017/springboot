package springboot;

import com.alibaba.fastjson.JSON;
import com.xxy.elasticsearch.indexes.People;
import com.xxy.elasticsearch.leetcode.Solution;
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
    @Autowired
    private Solution solution;

    @Test
    public  void test2(){
        int[] numbs={1,1,2,2,44,4,44,4,5};
        solution.removeDuplicates(numbs);
    }
    //@Test
    public void test(){

    }

}
