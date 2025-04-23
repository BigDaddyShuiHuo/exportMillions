package cn.hwz.learn.exportmillions.service.impl;


import cn.hwz.learn.exportmillions.entity.MillionDataExport;
import cn.hwz.learn.exportmillions.mapper.MillionDataExportMapper;
import cn.hwz.learn.exportmillions.util.MonitorUtil;
import com.opencsv.CSVWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
* @author 59755
* @description 针对表【million_data_export】的数据库操作Service实现
*/
@Service
@Slf4j
public class MillionDataExportServiceImpl {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private MillionDataExportMapper mapper;

    /**
     * 测试
     * @param id
     * @return
     */
    public MillionDataExport selectById(Long id){
        return mapper.selectById(id);
    }


   // @Transactional
    public void testExport(String filePath){
        // 3秒统计一次cpu占用，内存占用
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(()->{
            MonitorUtil.getProcessCpuLoad();
            MonitorUtil.getSystemCpuLoad();
            MonitorUtil.getMemoryUsage();
        },1,3, TimeUnit.SECONDS);
        // 打印耗时
        long start = System.currentTimeMillis();
        exportCursorToCsv(filePath);
        log.debug("耗时：{}",System.currentTimeMillis()-start);
        executor.shutdown();

    }

    /**
     * csv文件导入工具类
     */
    public void exportCursorToCsv(String filePath){
        try(
                Writer writer = Files.newBufferedWriter(Paths.get(filePath));
                CSVWriter csvWriter = new CSVWriter(writer,
                        CSVWriter.DEFAULT_SEPARATOR,
                        CSVWriter.DEFAULT_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END)
                )
        {

            Cursor<MillionDataExport> cursor = null;
            try {
                cursor
                        = sqlSessionTemplate.selectCursor("cn.hwz.learn.exportmillions.mapper.MillionDataExportMapper.selectCursor");
                int batchSize = 0;
                for (MillionDataExport data:cursor){
                    csvWriter.writeNext(new String[]{data.getName(),
                            data.getAge(),
                            data.getData1(),
                            data.getData2(),
                            data.getData3(),
                            data.getData4(),
                            data.getData5(),
                            data.getData6(),
                            data.getData7()});
                    batchSize++;
                    if (batchSize%1000==0){
                        csvWriter.flush();
                    }
                }
                csvWriter.flush();
            }finally {
                if (cursor!=null){
                    cursor.close();
                }
            }
        }catch (Exception e){
            log.error("导出异常:{}",e);
        }

    }



}




