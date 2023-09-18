package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Alias("noticefileVO")
public class Notice_FileVO implements Serializable{
   
   public Notice_FileVO(MultipartFile item) {
      super();
      this.item = item;
      setNotice_file_size(item.getSize());
      setNotice_file_fancysize(FileUtils.byteCountToDisplaySize(item.getSize()));
      setNotice_file_name(item.getOriginalFilename());
      setNotice_file_mime(item.getContentType());
      setNotice_file_savename(UUID.randomUUID().toString());
   }
   
   private Long notice_file_no;
   private String notice_file_name;
   private String notice_file_savename;
   private String notice_file_mime;
   private Long notice_file_size;
   private String notice_file_fancysize;
   private Long notice_no;
   private MultipartFile item;
}