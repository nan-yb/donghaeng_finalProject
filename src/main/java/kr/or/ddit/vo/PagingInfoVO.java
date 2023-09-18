package kr.or.ddit.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *	 totalRecord 와 currentPage 를 결정하면, 나머지 속성들이 연산됨.
 *   setTotalRecord/setCurrentPage 호출 필요. 
 *
 */
@Data
@NoArgsConstructor
@Alias("pagingVO")
public class PagingInfoVO<T> {
	
	
	public PagingInfoVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}
	private long number;
	private long totalRecord;
	private int screenSize = 10;
	private int blockSize = 5;
	private long currentPage;
	private long totalPage;
	private long startPage;
	private long endPage;
	private long startRow;
	private long endRow;
	private List<T> dataList;
	private T searchVO;
	private String searchWord;
	private String searchType;
	private String funcName = "paging";
	
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = totalRecord%screenSize==0?totalRecord/screenSize
							:totalRecord/screenSize + 1;
	}
	
	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize-1);
		startPage = (currentPage-1)/blockSize * blockSize + 1;
		endPage = startPage + (blockSize - 1);
	}
	
	public String getPagingHTML() {
		
		String pattern = "<li><a class='pgn__num' href='javascript:"+funcName+"(%d);'>%s</a></li>";
		String pattern2 = "<li><a class='pgn__num current' href='javascript:"+funcName+"(%d);'>%s</a></li>";
		StringBuffer html = new StringBuffer();
		html.append("<div class='row'><div class='col-full'><nav class='pgn'><ul>");
		
		if(blockSize<startPage) {
			html.append(String.format(pattern, startPage-blockSize, "이전"));
		}
		if(endPage > totalPage) endPage = totalPage; 
		
		for(long page = startPage; page <= endPage; page++) {
			String pageStr = page+"";
			if(page == currentPage) {
				html.append(String.format(pattern2,  page, pageStr));
			}else{
				html.append(String.format(pattern,  page, pageStr));
			}
		}
		if(endPage<totalPage) {
			html.append(String.format(pattern, endPage+1, "다음"));
		}
		html.append("</ul></nav></div></div>");
		return html.toString();		
		
	}
	
}












