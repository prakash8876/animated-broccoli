package impl;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles(resolver = TestProfleResolver.class)
@SpringBootTest(classes = TestConfiguration.class)
@RunWith(SpringRunner.class)
@TestPropertySource({"classpath:application-test.yml"})
public class BaseConfiguration {

}
