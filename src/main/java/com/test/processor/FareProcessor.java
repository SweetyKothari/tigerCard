package com.test.processor;

import com.opencsv.CSVReader;
import com.test.dto.CommuteDetailDTO;
import com.test.service.FareCalculatorService;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is class responsible from reading file & converting into DTO
 */
public class FareProcessor {

    private static FareCalculatorService fareCalculatorService= new FareCalculatorService();

    static Logger logger = LoggerFactory.getLogger(FareProcessor.class);

    public double processNDeriveFare(String fileName){
        return fareCalculatorService.calculate(readFileNCreateDTO(fileName));
    }

    /**
     * This method reads file & convert to CommuteDetailDTO
     * @param fileName
     * @return
     */
    public List<CommuteDetailDTO> readFileNCreateDTO(String fileName){
        FileReader filereader = null;
        List<CommuteDetailDTO> commuteDetailDTOS=new ArrayList<>();
        if(!fileName.endsWith(".csv")) {
            logger.info("Please provide csv file as an input ");
            System.exit(1);
        }
        try {
            filereader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            logger.error("File is not present at given location ,please provide full path");
            System.exit(1);
            return null;
        }
        CSVReader csvReader = new CSVReader(filereader);
        String[] nextRecord;
        int i=0;
        // we are going to read data line by line
        while (true) {
            try {
                if (!((nextRecord = csvReader.readNext()) != null)) break;
                   if(i==0){
                       i++;
                       continue;
                   }
                   if(nextRecord.length>1) {
                       try {
                           CommuteDetailDTO commuteDetailDTO = new CommuteDetailDTO.CommuteDetailDTOBuilder(nextRecord[0], nextRecord[1],
                                   Integer.parseInt(nextRecord[2]), Integer.parseInt(nextRecord[3])).build();
                           commuteDetailDTOS.add(commuteDetailDTO);
                       }
                       catch(Exception ex){
                           logger.error(ex.getMessage());
                           System.exit(1);
                       }
                   }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return commuteDetailDTOS;
    }

}
