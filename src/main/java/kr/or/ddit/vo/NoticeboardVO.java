package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Alias("noticeboardVO")
public class NoticeboardVO implements Serializable{
   
   
   private Long notice_no;
   
   @NotBlank
   private String notice_title;
   @NotBlank
   private String notice_content;
   private String notice_date;
   private Long notice_view_count;
   private String person_id;
   
   private List<PersonVO> personList;
   private List<Notice_FileVO> noticeFileList;
   private Long[] delFiles;
   
   private MultipartFile[] notice_file;
   

   public void setNotice_file(MultipartFile[] notice_file) {
      this.notice_file = notice_file;
      List<Notice_FileVO> noticeFileList = null;
      if (notice_file == null)
         return;
      noticeFileList = new ArrayList<>();
      for (MultipartFile item : notice_file) {
         if (StringUtils.isBlank(item.getOriginalFilename()))
            continue;
         noticeFileList.add(new Notice_FileVO(item));
      }
      if (noticeFileList.size() > 0)
         this.noticeFileList = noticeFileList;
   }
}