package cn.hwz.learn.exportmillions.mapper;

import cn.hwz.learn.exportmillions.entity.MillionDataExport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.stereotype.Repository;


/**
* @author 59755
* @description 针对表【million_data_export】的数据库操作Mapper
* @Entity cn.hwz.learn.exportmillions.entity.MillionDataExport
*/

@Mapper
public interface MillionDataExportMapper  {
    MillionDataExport selectById(long id);

    Cursor<MillionDataExport> selectCursor();
}




