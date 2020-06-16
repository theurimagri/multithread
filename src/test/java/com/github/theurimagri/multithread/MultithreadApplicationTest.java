package com.github.theurimagri.multithread;

import com.carrotsearch.junitbenchmarks.AbstractBenchmark;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.github.theurimagri.multithread.model.SaleCounter;
import com.github.theurimagri.multithread.service.Processor;
import com.github.theurimagri.multithread.util.XMLPrinter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
public class MultithreadApplicationTest extends AbstractBenchmark {

    private final boolean enablePrinting = false;

    @Autowired
    @Qualifier("serial")
    Processor serialSalesProcessor;

    @Autowired
    @Qualifier("stream")
    Processor streamSalesProcessor;

    @Autowired
    @Qualifier("database")
    Processor databaseSalesProcessor;

    @Autowired
    @Qualifier("countDownLatch")
    Processor countDownLatchSalesProcessor;

    @Test
    @BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5)
    public void serialExecution() {
        SaleCounter saleCounter = serialSalesProcessor.process();
        print(saleCounter);
    }

    @Test
    @BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5)
    public void streamExecution() {
        SaleCounter saleCounter = streamSalesProcessor.process();
        print(saleCounter);
    }

    @Test
    @BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5)
    public void databaseExecution() {
        SaleCounter saleCounter = databaseSalesProcessor.process();
        print(saleCounter);
    }

    @Test
    @BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5)
    public void countDownLatchExecution() {
        SaleCounter saleCounter = countDownLatchSalesProcessor.process();
        print(saleCounter);
    }

    private void print(SaleCounter saleCounter) {
        if(enablePrinting) {
            XMLPrinter.print(saleCounter);
        }
    }
}
