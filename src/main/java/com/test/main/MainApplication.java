package com.test.main;

import com.test.processor.FareProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApplication {
    static Logger logger = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        //read file name as an argument
        if(args.length < 1) {
            logger.info("Please run command by passing file name as an argument \n java -jar <jarfileName> <filePath>");
            System.exit(1);
        }
       double val= new FareProcessor().processNDeriveFare(
              args[0]);
        logger.info("Total Fare : "+val);
    }
}
