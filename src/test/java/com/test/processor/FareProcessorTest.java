package com.test.processor;

import com.test.dto.CommuteDetailDTO;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FareProcessorTest {

    Path resourceDirectory = Paths.get("src","test","resources");
    FareProcessor fareProcessor= new FareProcessor();

    @Test
    public void testProcessNDeriveFare(){
        double val=fareProcessor.processNDeriveFare(resourceDirectory.toAbsolutePath().toString()+"/testInput.csv");
        assertEquals(val,120);
    }

    @Test
    public void testReadFileNCreateDTO(){
        List<CommuteDetailDTO> commuteDetailDTOList=fareProcessor.readFileNCreateDTO(resourceDirectory.toAbsolutePath().toString()+"/testInput.csv");
        assertEquals(commuteDetailDTOList.size(),6);
    }
    @Test
    public void testProcessNDeriveFare_2(){
        double val=fareProcessor.processNDeriveFare(resourceDirectory.toAbsolutePath().toString()+"/testInput2.csv");
        assertEquals(val,835);
    }

    @Test
    public void testReadFileNCreateDTO_2(){
        List<CommuteDetailDTO> commuteDetailDTOList=fareProcessor.readFileNCreateDTO(resourceDirectory.toAbsolutePath().toString()+
                "/testInput2.csv");
        assertEquals(commuteDetailDTOList.size(),35);
    }
    @Test
    public void testProcessNDeriveFare_3(){
        double val=fareProcessor.processNDeriveFare(resourceDirectory.toAbsolutePath().toString()+"/testInput3.csv");
        assertEquals(val,600);
    }

    @Test
    public void testReadFileNCreateDTO_3(){
        List<CommuteDetailDTO> commuteDetailDTOList=fareProcessor.readFileNCreateDTO(resourceDirectory.toAbsolutePath().toString()+
                "/testInput3.csv");
        assertEquals(commuteDetailDTOList.size(),37);
    }
    @Test
    public void testProcessNDeriveFare_4(){
        double val=fareProcessor.processNDeriveFare(resourceDirectory.toAbsolutePath().toString()+"/testInput4.csv");
        assertEquals(val,715);
    }

    @Test
    public void testReadFileNCreateDTO_4(){
        List<CommuteDetailDTO> commuteDetailDTOList=fareProcessor.readFileNCreateDTO(resourceDirectory.toAbsolutePath().toString()+
                "/testInput4.csv");
        assertEquals(commuteDetailDTOList.size(),31);
    }

}
