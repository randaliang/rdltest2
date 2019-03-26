package com.superhope;

import java.io.File;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.superhope.parsesql.CsvService;

@SpringBootApplication
@EnableTransactionManagement

public class Application  implements CommandLineRunner{
	
	@Autowired 
	private CsvService cs;
	
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
//		cs.parseCsvFile( new File("D:\\temp\\江苏\\凭证库柜面320000\\PB_PAY_VOUCHER.csv"),"PB_PAY_VOUCHER" );
	}
}
