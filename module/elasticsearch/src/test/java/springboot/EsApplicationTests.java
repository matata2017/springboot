package springboot;

import com.alibaba.fastjson.JSON;
import com.xxy.elasticsearch.indexes.People;
import com.xxy.elasticsearch.leetcode.MyLinkedList;
import com.xxy.elasticsearch.leetcode.SinglyListNode;
import com.xxy.elasticsearch.leetcode.Solution;
import com.xxy.elasticsearch.service.elasticsearchService;
import org.apache.log4j.Logger;
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
import sun.rmi.runtime.Log;

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

    private static final Logger log = Logger.getLogger(EsApplicationTests.class);

    @Test
    public  void test2(){
        int[] numbs={1,1,2,2,44,4,44,4,5};
        solution.removeDuplicates(numbs);
    }
    @Test
    public void test(){
        int[] numbs={7,1,5,3,6,4};
        int pro=  solution.maxProfit(numbs);
        log.info("============="+pro);

    }
    @Test
    public void test3(){
        int[] numbs={7,1,5,3,6,4};
        solution.rotate1(numbs,2);
    }

    @Test
    public void test4(){
        int[] numbs={7,1,5,3,6,4};
        solution.singleNumber(numbs);
    }

    @Test
    public void test5(){
        int[] numbs={1,1,2,2};
        int[] numb2={2,2};
        solution.intersect(numbs,numb2);
    }

    @Test
    public void test6(){
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(8);
        linkedList.get(1);
        linkedList.addAtTail(81);
        linkedList.deleteAtIndex(2);
        linkedList.addAtHead(26);
        linkedList.deleteAtIndex(2);
        linkedList.get(1);
    }
}
