package cn.itcast.usermanager.view;

import cn.itcast.usermanager.pojo.Constants;
import cn.itcast.usermanager.pojo.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 自定义视图
 * 继承excel的抽象类，重写buildExcelDocument
 * excel也可以作为一种视图使用
 *
 * 虽然excel可作视图使用，但必须遵循以下springmvc的规则才可以：
 * 1、自定义一个视图（UserExcel）并继承AbstractExcelView抽象类
 * 2、把自定义视图在springmvc的配置文件（usermanage-servlet.xml）中注册
 * 3、定义视图解析器，解析excel视图
 */
public class UserExcelView extends AbstractXlsView{

    @Override//model是怎么变成Map的
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        //从model对象中获取userList
        List<User> userList = (List<User>) model.get("userList");
        //创建Excel的sheet
        Sheet sheet = workbook.createSheet("会员列表");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("用户名");
        header.createCell(2).setCellValue("姓名");
        header.createCell(3).setCellValue("年龄");
        header.createCell(4).setCellValue("性别");
        header.createCell(5).setCellValue("出生日期");
        header.createCell(6).setCellValue("创建时间");
        header.createCell(7).setCellValue("更新时间");


        /*填充数据*/
        int rowNum=1;
        for (User user : userList) {
            Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getUserName());
            row.createCell(2).setCellValue(user.getName());
            row.createCell(3).setCellValue(user.getAge());
            String sexStr;
            if(user.getSex()==1){
                sexStr="男";
            }else if ((user.getSex()==2)){
                sexStr="女";
            }else{
                sexStr="未知";
            }
            row.createCell(4).setCellValue(sexStr);
            row.createCell(5).setCellValue(new DateTime(user.getBirthday()).toString(Constants.DATE));
            row.createCell(6).setCellValue(new DateTime(user.getCreated()).toString(Constants.DATE_TIME));
            row.createCell(7).setCellValue(new DateTime(user.getUpdated()).toString(Constants.DATE_TIME));

            rowNum++;
        }
        //设置响应头信息。以附件的形式下载并且指定文件名
        response.setHeader("Content-Disposition","attachment;filename=" + new String("会员列表.xls".getBytes(),"ISO-8859-1"));
    }
}
