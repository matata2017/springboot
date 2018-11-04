package springboot;

import com.xxy.springboot.designmoudle.man;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = GirlApplicationTests.class)
@AutoConfigureTestEntityManager
@DataJpaTest
@EnableAutoConfiguration
@AutoConfigureTestDatabase(replace = NONE)
@ComponentScan(value = {"com.xxy.**"})
@EnableJpaRepositories({"com.xxy.**"})
@EntityScan(basePackages = {"com.xxy.**"})
//@Import(TestConfig.class)
public class GirlApplicationTests {
    private static final Logger log = Logger.getLogger(GirlApplicationTests.class);
    @Test
    public void test(){
        List<Object> list = new ArrayList<Object>();

        for (int i = 0; i < 100000000; i++) {

            list.add(new byte[1024*1024]);

        }
    }

    @Test
    public void test2(){
        long start =System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            man a = new man();
            a.calls(i);
        }
        long end =System.currentTimeMillis();
        log.info("================"+(end-start));
        Runtime.getRuntime().freeMemory();
        Runtime.getRuntime().totalMemory();
    }

}
