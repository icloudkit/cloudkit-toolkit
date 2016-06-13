package net.cloudkit.toolkit.application;

import net.cloudkit.toolkit.SuperPass;
import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class PersistentRepositoryServiceImpl implements PersistentRepositoryService {

    //QP系统数据库处理类
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 写QP暂存任务队列
     */
    @Transactional
    @Override
    public void save(Map<String, Object> dataMap) throws Exception {
        Map<String, String> cdlBillHead = (Map<String, String>) dataMap.get("CdlBillHead");
        List<Map<String, String>> cdlBillLists = (List<Map<String, String>>) dataMap.get("CdlBillLists");
        List<Map<String, String>> cdlBillDocs = (List<Map<String, String>>) dataMap.get("CdlBillDocs");

        // EPROT_NO CUSTOMS_NO IE_TYPE IE_PORT IE_DATE HAND_BOOK_NO TradeCode TradeName OwnerCode OwnerName
        // BillSeq ListNo IEFlag IEPort IEDate RecordsNo TradeCode TradeName OwnerCode OwnerName TrafMode, ShipName, VoyageNo, BillNo, TradeMode, TradeCountry, DestinationPort, DistrictCode, PackNum, WrapType, GrossWt, NetWt, ListType, ListStat, DocuCodeCom, AgentCode, AgentName, DeclCustom, DeclDate, GType


        /// if \(\".*\"\.equals\(elemtHeadFeild\)\) \{

        List<String> paramList = new ArrayList<>();
        paramList.add(cdlBillHead.get("BillSeq"));
        // paramList.add(cdlBillHead.get("ListNo"));
        paramList.add(cdlBillHead.get("IEFlag"));
        paramList.add(cdlBillHead.get("IEPort"));
        paramList.add(cdlBillHead.get("IEDate"));// int
        paramList.add(cdlBillHead.get("RecordsNo"));
        paramList.add(cdlBillHead.get("TradeCode"));
        paramList.add(cdlBillHead.get("TradeName"));
        // paramList.add(cdlBillHead.get("OwnerCode"));
        // paramList.add(cdlBillHead.get("OwnerName"));
        paramList.add(cdlBillHead.get("TrafMode"));
        paramList.add(cdlBillHead.get("ShipName"));
        paramList.add(cdlBillHead.get("VoyageNo"));
        paramList.add(cdlBillHead.get("BillNo"));
        paramList.add(cdlBillHead.get("TradeMode"));
        paramList.add(cdlBillHead.get("TradeCountry"));
        paramList.add(cdlBillHead.get("DestinationPort"));
        paramList.add(cdlBillHead.get("DistrictCode"));
        paramList.add(cdlBillHead.get("PackNum"));
        paramList.add(cdlBillHead.get("WrapType"));
        paramList.add(cdlBillHead.get("GrossWt"));
        paramList.add(cdlBillHead.get("NetWt"));
        paramList.add(cdlBillHead.get("ListType"));


        // 预录入编号
        paramList.add(cdlBillHead.get("ListNo"));
        // 海关编号
        paramList.add(cdlBillHead.get("ListNo"));
        // 录单编号
        paramList.add(cdlBillHead.get("ListNo"));

        paramList.add(cdlBillHead.get("DeclDate"));
        paramList.add("7");
        // 申报单位代码
        paramList.add(cdlBillHead.get("AgentCode"));
        // 申报单位名称
        paramList.add(cdlBillHead.get("AgentName"));
        paramList.add(cdlBillHead.get("GType"));
        paramList.add("3");
        paramList.add(String.valueOf(System.currentTimeMillis()));
        // 货物项数
        paramList.add(String.valueOf(cdlBillLists.size()));
        // 货物总金额
        paramList.add("0");
        // 币种
        paramList.add("");
        paramList.add("766350979");


        paramList.add(cdlBillHead.get("ListStat"));
        paramList.add(cdlBillHead.get("DocuCodeCom"));
        paramList.add(cdlBillHead.get("AgentCode"));
        paramList.add(cdlBillHead.get("AgentName"));
        paramList.add(cdlBillHead.get("DeclCustom"));


        // ListNo, IEFlag, IEPort, IEDate, RecordsNo, TradeCode, TradeName, OwnerCode, OwnerName, TrafMode, ShipName, VoyageNo, BillNo, TradeMode, TradeCountry, DestinationPort, DistrictCode, PackNum, WrapType, GrossWt, NetWt, ListType, ListStat, DocuCodeCom, AgentCode, AgentName, DeclCustom, DeclDate, GType
        jdbcTemplate.update("INSERT INTO CDL_HEAD_DOWNLOAD(EPROT_NO,IE_TYPE,IE_PORT,IE_DATE, HAND_BOOK_NO," +
                "EXPORT_CODE,EXPORT_NAME,TRANS_TYPE_CODE,TRANS_TOOL_NAME,VOYAGE_NO,BILL_NO,TRADE_TYPE_CODE," +
                "COUNTRY_CODE,DEST_PORT_CODE,REGION_CODE,TOTAL_PACKEGES,PACKAGE_TYPE_CODE,TOTAL_GROSS_WEIGHT," +
                "TOTAL_NET_WEIGHT,CDL_TYPE,PRE_CUSTOMS_NO,CUSTOMS_NO,PRE_INPUT_NO,DECL_DATE,DECL_PORT," +
                "DECL_COM_CODE,DECL_COM_NAME,PRODUCT_FLAG,SOURCES,CREATE_TIME,GOODS_NUM,TOTAL_AMOUNT," +
                "CURRENCY_CODE,COMPANY_CODE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?)", paramList.toArray());

        // GNo ItemNo CodeTs GName GModel GQty GUnit DeclPrice DeclTotal TradeCurr OriginalalCountry DutyMode Unit1 Unit2
        for(Map<String, String> cdlBillList : cdlBillLists) {

            List<String> paramList2 = new ArrayList<>();
            paramList.add(cdlBillHead.get("BillSeq"));
            paramList.add(cdlBillList.get("GNo"));
            paramList.add(cdlBillList.get("ItemNo"));
            paramList.add(cdlBillList.get("CodeTs"));
            paramList.add(cdlBillList.get("GName"));
            paramList.add(cdlBillList.get("GModel"));
            paramList.add(cdlBillList.get("GQty"));
            paramList.add(cdlBillList.get("GUnit"));
            paramList.add(cdlBillList.get("DeclPrice"));
            paramList.add(cdlBillList.get("DeclTotal"));
            paramList.add(cdlBillList.get("TradeCurr"));
            paramList.add(cdlBillList.get("OriginalalCountry"));
            paramList.add(cdlBillList.get("DutyMode"));
            paramList.add(cdlBillList.get("Unit1"));
            paramList.add(cdlBillList.get("Unit2"));

            jdbcTemplate.update("INSERT INTO CDL_GOODS_DOWNLOAD(EPROT_NO,GOODS_SEQ," +
                    "GOODS_ITEM,HSCODE_TS,GOODS_NAME,GOODS_MODEL,QTY,UNIT,PRICE,AMOUNT,CURRENCY,DESTINATION_CODE," +
                    "TAX_TYPE,UNIT_1,UNIT_2) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", paramList2.toArray());
        }

        /*
        // DocCode CertNo
        for(Map<String, String> cdlBillDoc : cdlBillDocs) {
            jdbcTemplate.update("INSERT INTO cdl_head_download (?, ?, ?) VALUES(?, ?, ?)", new ArrayList(cdlBillDoc.values()).toArray());
        }
        */
    }


    /**
     * 调用 WebService
     *
     * @param serviceName
     * @param requestContext
     * @param requestData
     * @param responseData
     * @return
     * @throws MalformedURLException
     */
    public static byte[] invoke(String serviceName, byte[] requestContext, byte[] requestData, Holder<byte[]> responseData) throws MalformedURLException {

        // http://220.181.191.7/SuperPass/SuperPass?wsdl
        // 调用外部接口
        URL url = new URL("http://sptrade.chinaport.gov.cn/SuperPassCdl/SuperPass?wsdl");
        QName qname = new QName("http://www.cneport.com/webservices/superpass", "SuperPass");
        Service service = Service.create(url, qname);
        SuperPass superPass = service.getPort(SuperPass.class);
        // Holder<byte[]> responseData = new Holder<byte[]>();
        byte[] result = superPass.service(serviceName, requestContext, requestData, responseData);

        // System.out.println("responseContext: \n" + new String(result) + "\nresponseData: \n" + new String(responseData.value));
        return result;
    }

    public static void downloadList() throws IOException {
        String serviceName = "eport.superpass.cdl.QueryEnableCdl2EntryService";
        byte[] requestContext = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><RequestContext><Group name=\"SystemInfo\"><Key name=\"DEP_CODE_CHG\">5300</Key><Key name=\"OperatorName\">吕亚玲</Key><Key name=\"ENT_TYPE\">3</Key><Key name=\"SaicSysNo\">766350979</Key><Key name=\"SessionId\">C27462BEEA063EA14AC7E4FE8230F651</Key><Key name=\"CertNo\">df5eed</Key><Key name=\"ClientId\">5300002150968</Key><Key name=\"REG_CO_CGAC\">4403180237</Key><Key name=\"DEP_IN_CODE\">5300</Key><Key name=\"NAME_FULL\">深圳市巨航国际物流有限公司</Key><Key name=\"IcCode\">8320000014608</Key><Key name=\"ENT_SEQ_NO\">000000000000315537</Key></Group><Group name=\"DataPresentation\"><Key name=\"EncryptAlgorithm\" /><Key name=\"CompressAlgorithm\" /><Key name=\"SignatureAlgorithm\" /></Group></RequestContext>".getBytes();
        byte[] requestData = ("<?xml version=\"1.0\"?>\n" +
                "<QueryEnableCdl2EntryRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "  <DeclDateBegin>20160501</DeclDateBegin>\n" +
                "  <DeclDateEnd>20160531</DeclDateEnd>\n" +
                "  <TradeBondedFlag>01</TradeBondedFlag>\n" +
                "  <AgentCode>4403180237</AgentCode>\n" +
                "  <TypistUnitCode>766350979$4403180237</TypistUnitCode>\n" +
                "  <ClientDate>20160603</ClientDate>\n" +
                "  <IEPort />\n" +
                "  <TradeMode />\n" +
                "  <TrafMode />\n" +
                "  <IEFlag>I</IEFlag>\n" +
                "  <OwnerCode />\n" +
                "  <TradeCode>4403944494</TradeCode>\n" +
                "</QueryEnableCdl2EntryRequest>").getBytes();
        Holder<byte[]> responseData = new Holder<>();

        byte[] result = invoke(serviceName, requestContext, requestData, responseData);
        // System.out.println(new String(result));
        IOUtils.write(responseData.value, new FileOutputStream(new File("D:\\temp\\EnableCdl2EntryLists.xml")));
    }

    public static void downloadDetail(String billSeq) throws IOException {
        String serviceName2 = "eport.superpass.cdl.QueryCdlDetailService";
        byte[] requestContext2 = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?><RequestContext><Group name=\"SystemInfo\"><Key name=\"DEP_CODE_CHG\">5300</Key><Key name=\"OperatorName\">吕亚玲</Key><Key name=\"ENT_TYPE\">3</Key><Key name=\"SaicSysNo\">766350979</Key><Key name=\"SessionId\">C27462BEEA063EA14AC7E4FE8230F651</Key><Key name=\"CertNo\">df5eed</Key><Key name=\"ClientId\">5300002150968</Key><Key name=\"REG_CO_CGAC\">4403180237</Key><Key name=\"DEP_IN_CODE\">5300</Key><Key name=\"NAME_FULL\">深圳市巨航国际物流有限公司</Key><Key name=\"IcCode\">8320000014608</Key><Key name=\"ENT_SEQ_NO\">000000000000315537</Key></Group><Group name=\"DataPresentation\"><Key name=\"EncryptAlgorithm\" /><Key name=\"CompressAlgorithm\" /><Key name=\"SignatureAlgorithm\" /></Group></RequestContext>".getBytes();
        byte[] requestData2 = ("<?xml version=\"1.0\"?>\n" +
                "<QueryCdlDetailRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n" +
                "  <BillSeq>" + billSeq + "</BillSeq>\n" +
                "</QueryCdlDetailRequest>").getBytes();
        Holder<byte[]> responseData2 = new Holder<>();

        byte[] result2 = invoke(serviceName2, requestContext2, requestData2, responseData2);
        // System.out.println(new String(result));
        IOUtils.write(responseData2.value, new FileOutputStream(new File("D:\\temp\\bill\\" + billSeq + ".xml")));
    }


    public static void download() throws IOException {
        downloadList();

        // XML List
        Map<String, List<String>> dataMap = new HashMap<String, List<String>>();
        List<String> messageList = null;

        String key = null;

        // MESSAGE_CONFIG
        // 创建InputStream
        InputStream in = null;
        try {

            // 创建StAX分析工厂
            XMLInputFactory xif = XMLInputFactory.newInstance();
            // 创建分析器
            XMLStreamReader reader = xif.createXMLStreamReader(new FileInputStream(new File("D:\\temp\\EnableCdl2EntryLists.xml")));

            // 迭代
            while (reader.hasNext()) {
                // 读取下一个事件
                int event = reader.next();

                // 如果这个事件是元素开始
                // if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                if (event == XMLStreamReader.START_ELEMENT) {
                    if ("BillSeq".equals(reader.getLocalName())) {
                        // System.out.println(reader.getElementText());
                        String billSeq = reader.getElementText();
                        downloadDetail(billSeq);
                    }

                    // DeclCustom
                    // ListType
                    // IEPort
                    // IEDate
                    // TradeMode
                    // ListNo
                }

                if (event == XMLStreamReader.END_ELEMENT) {
                    if ("BillSeq".equals(reader.getLocalName())) {

                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
