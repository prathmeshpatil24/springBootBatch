package com.batchExample.configuration;

import com.batchExample.entity.Product;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.infrastructure.item.file.FlatFileItemReader;
import org.springframework.batch.infrastructure.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.Writer;

@Configuration
public class BatchConfig {

    //A Job represents the complete batch process.
    @Bean
    public Job jobBean(JobRepository jobRepository, // job repo
                       JobCompletionNotification listener,// listener
                       Step step){ //steps

        return new JobBuilder("job", jobRepository)
                .listener(listener)
                .start(step)
                .build();

    }

    //A Step is one unit of work inside a Job.
   /*If CSV has 100 records

Batch 1 → 20 records
Batch 2 → 20 records
Batch 3 → 20 records
Batch 4 → 20 records
Batch 5 → 20 records
*/

    @Bean
    public Step steps(
            JobRepository jobRepository,
            //DataSourceTransactionManager dataSourceTransactionManager,
            ItemReader<Product> reader,
            ItemProcessor<Product, Product>processor,
            ItemWriter<Product> writer
    ){
        return  new StepBuilder("jobStep", jobRepository)
                .<Product,Product>chunk(20) // give data in chunks of 20 records
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    //reader
    //It reads data from a source.
    @Bean
    public FlatFileItemReader<Product>reader(){
        return  new FlatFileItemReaderBuilder<Product>()
                .name("itemReader")
                .resource(new ClassPathResource("data.csv"))
                .linesToSkip(1)// Skip header row
                .delimited() //CSV is comma separated
                .names("productId", "title", "description", "price", "discount") //
                .targetType(Product.class)
                .build();
    }

    //    processor

    @Bean
    public ItemProcessor<Product, Product> itemProcessor() {
        return new CustomItemProcessor();
    }

//    writer
// Writer saves processed data to database.
    @Bean
    public ItemWriter<Product> itemWriter(DataSource dataSource) {

        return new JdbcBatchItemWriterBuilder<Product>()
                .sql("insert into products(product_id,title,description,price,discount)" +
                        "values(:productId, :title, :description, :price, :discount)"
                )
                .dataSource(dataSource)
                .beanMapped()
                .build();

    }

}
