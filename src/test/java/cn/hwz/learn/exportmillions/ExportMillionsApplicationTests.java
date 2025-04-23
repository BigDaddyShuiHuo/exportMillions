package cn.hwz.learn.exportmillions;

import cn.hwz.learn.exportmillions.entity.MillionDataExport;
import cn.hwz.learn.exportmillions.mapper.MillionDataExportMapper;
import cn.hwz.learn.exportmillions.service.impl.MillionDataExportServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ExportMillionsApplicationTests {

    @Autowired
    private MillionDataExportMapper millionDataExportMapper;

    @Autowired
    private MillionDataExportServiceImpl millionDataExportService;

    @Test
    void contextLoads() {
        MillionDataExport millionDataExport = millionDataExportMapper.selectById(1L);
        log.debug("结果:{}",millionDataExport);
    }

    @Test
    void testCursor(){
        millionDataExportService.testExport("D:\\test.csv");
    }



}
