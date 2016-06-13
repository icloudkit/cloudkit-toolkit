/*
 * Copyright (C) 2016. The CloudKit Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.cloudkit.toolkit;

import net.cloudkit.toolkit.application.PersistentRepositoryService;
import net.cloudkit.toolkit.application.PersistentRepositoryServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.*;

/**
 * QP 参数查询
 */
@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = {"classpath*:application-context.xml", "classpath*:application-context-jpa-management.xml"})
@ContextConfiguration(locations = {"classpath*:application-context*.xml"})
@ActiveProfiles("development")
public class DownloadTest {

    @Resource
    private PersistentRepositoryService persistentRepositoryService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    @Transactional
    @Test
    public void test() throws Exception {



        Map<String, Object> cdl = new HashMap<>();
        Map<String, String> cdlBillHead = null;
        Map<String, String> cdlBillList = null;
        List<Map<String, String>> cdlBillLists = new ArrayList<>();
        Map<String, String> cdlBillDoc = null;
        List<Map<String, String>> cdlBillDocs = new ArrayList<>();

        // MESSAGE_CONFIG
        // 创建InputStream
        InputStream in = null;
        try {

            // 创建StAX分析工厂
            XMLInputFactory xif = XMLInputFactory.newInstance();
            // 创建分析器
            XMLStreamReader reader = xif.createXMLStreamReader(new FileInputStream(new File("D:\\temp\\bill\\CDL100000006531646.xml")));

            // 迭代
            while (reader.hasNext()) {
                // 读取下一个事件
                int event = reader.next();

                // 如果这个事件是元素开始
                // if (reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                if (event == XMLStreamReader.START_ELEMENT) {
                    // CdlBill/CdlBillHead
                    if ("CdlBillHead".equals(reader.getLocalName())) {
                        cdlBillHead = new LinkedHashMap<>();
                    }
                    {
                        // BillSeq
                        if ("BillSeq".equals(reader.getLocalName())) {
                            cdlBillHead.put("BillSeq", reader.getElementText());
                        }
                        // ListNo
                        if ("ListNo".equals(reader.getLocalName())) {
                            cdlBillHead.put("ListNo", reader.getElementText());
                        }
                        // IEFlag
                        if ("IEFlag".equals(reader.getLocalName())) {
                            cdlBillHead.put("IEFlag", reader.getElementText());
                        }
                        // IEPort
                        if ("IEPort".equals(reader.getLocalName())) {
                            cdlBillHead.put("IEPort", reader.getElementText());
                        }
                        // IEDate
                        if ("IEDate".equals(reader.getLocalName())) {
                            cdlBillHead.put("IEDate", reader.getElementText());
                        }
                        // RecordsNo
                        if ("RecordsNo".equals(reader.getLocalName())) {
                            cdlBillHead.put("RecordsNo", reader.getElementText());
                        }
                        // TradeCode
                        if ("TradeCode".equals(reader.getLocalName())) {
                            cdlBillHead.put("TradeCode", reader.getElementText());
                        }
                        // TradeName
                        if ("TradeName".equals(reader.getLocalName())) {
                            cdlBillHead.put("TradeName", reader.getElementText());
                        }
                        // OwnerCode
                        if ("OwnerCode".equals(reader.getLocalName())) {
                            cdlBillHead.put("OwnerCode", reader.getElementText());
                        }
                        // OwnerName
                        if ("OwnerName".equals(reader.getLocalName())) {
                            cdlBillHead.put("OwnerName", reader.getElementText());
                        }
                        // TrafMode
                        if ("TrafMode".equals(reader.getLocalName())) {
                            cdlBillHead.put("TrafMode", reader.getElementText());
                        }
                        // ShipName
                        if ("ShipName".equals(reader.getLocalName())) {
                            cdlBillHead.put("ShipName", reader.getElementText());
                        }
                        // VoyageNo
                        if ("VoyageNo".equals(reader.getLocalName())) {
                            cdlBillHead.put("VoyageNo", reader.getElementText());
                        }
                        // BillNo
                        if ("BillNo".equals(reader.getLocalName())) {
                            cdlBillHead.put("BillNo", reader.getElementText());
                        }
                        // TradeMode
                        if ("TradeMode".equals(reader.getLocalName())) {
                            cdlBillHead.put("TradeMode", reader.getElementText());
                        }
                        // TradeCountry
                        if ("TradeCountry".equals(reader.getLocalName())) {
                            cdlBillHead.put("TradeCountry", reader.getElementText());
                        }
                        // DestinationPort
                        if ("DestinationPort".equals(reader.getLocalName())) {
                            cdlBillHead.put("DestinationPort", reader.getElementText());
                        }
                        // DistrictCode
                        if ("DistrictCode".equals(reader.getLocalName())) {
                            cdlBillHead.put("DistrictCode", reader.getElementText());
                        }
                        // PackNum
                        if ("PackNum".equals(reader.getLocalName())) {
                            cdlBillHead.put("PackNum", reader.getElementText());
                        }
                        // WrapType
                        if ("WrapType".equals(reader.getLocalName())) {
                            cdlBillHead.put("WrapType", reader.getElementText());
                        }
                        // GrossWt
                        if ("GrossWt".equals(reader.getLocalName())) {
                            cdlBillHead.put("GrossWt", reader.getElementText());
                        }
                        // NetWt
                        if ("NetWt".equals(reader.getLocalName())) {
                            cdlBillHead.put("NetWt", reader.getElementText());
                        }
                        // ListType
                        if ("ListType".equals(reader.getLocalName())) {
                            cdlBillHead.put("ListType", reader.getElementText());
                        }
                        // ListStat
                        if ("ListStat".equals(reader.getLocalName())) {
                            cdlBillHead.put("ListStat", reader.getElementText());
                        }
                        // DocuCodeCom
                        if ("DocuCodeCom".equals(reader.getLocalName())) {
                            cdlBillHead.put("DocuCodeCom", reader.getElementText());
                        }
                        // AgentCode
                        if ("AgentCode".equals(reader.getLocalName())) {
                            cdlBillHead.put("AgentCode", reader.getElementText());
                        }
                        // AgentName
                        if ("AgentName".equals(reader.getLocalName())) {
                            cdlBillHead.put("AgentName", reader.getElementText());
                        }
                        // DeclCustom
                        if ("DeclCustom".equals(reader.getLocalName())) {
                            cdlBillHead.put("DeclCustom", reader.getElementText());
                        }
                        // DeclDate
                        if ("DeclDate".equals(reader.getLocalName())) {
                            cdlBillHead.put("DeclDate", reader.getElementText());
                        }
                        // GType
                        if ("GType".equals(reader.getLocalName())) {
                            cdlBillHead.put("GType", reader.getElementText());
                        }

                    }

                    // CdlBill/CdlBillLists/CdlBillList
                    if ("CdlBillList".equals(reader.getLocalName())) {
                        cdlBillList = new LinkedHashMap<>();
                    }
                    {
                        // GNo
                        if ("GNo".equals(reader.getLocalName())) {
                            cdlBillList.put("GNo", reader.getElementText());
                        }
                        // ItemNo
                        if ("ItemNo".equals(reader.getLocalName())) {
                            cdlBillList.put("ItemNo", reader.getElementText());
                        }
                        // CodeTs
                        if ("CodeTs".equals(reader.getLocalName())) {
                            cdlBillList.put("CodeTs", reader.getElementText());
                        }
                        // GName
                        if ("GName".equals(reader.getLocalName())) {
                            cdlBillList.put("GName", reader.getElementText());
                        }
                        // GModel
                        if ("GModel".equals(reader.getLocalName())) {
                            cdlBillList.put("GModel", reader.getElementText());
                        }
                        // GQty
                        if ("GQty".equals(reader.getLocalName())) {
                            cdlBillList.put("GQty", reader.getElementText());
                        }
                        // GUnit
                        if ("GUnit".equals(reader.getLocalName())) {
                            cdlBillList.put("GUnit", reader.getElementText());
                        }
                        // DeclPrice
                        if ("DeclPrice".equals(reader.getLocalName())) {
                            cdlBillList.put("DeclPrice", reader.getElementText());
                        }
                        // DeclTotal
                        if ("DeclTotal".equals(reader.getLocalName())) {
                            cdlBillList.put("DeclTotal", reader.getElementText());
                        }
                        // TradeCurr
                        if ("TradeCurr".equals(reader.getLocalName())) {
                            cdlBillList.put("TradeCurr", reader.getElementText());
                        }
                        // OriginalalCountry
                        if ("OriginalalCountry".equals(reader.getLocalName())) {
                            cdlBillList.put("OriginalalCountry", reader.getElementText());
                        }
                        // DutyMode
                        if ("DutyMode".equals(reader.getLocalName())) {
                            cdlBillList.put("DutyMode", reader.getElementText());
                        }
                        // Unit1
                        if ("Unit1".equals(reader.getLocalName())) {
                            cdlBillList.put("Unit1", reader.getElementText());
                        }
                        // Unit2
                        if ("Unit2".equals(reader.getLocalName())) {
                            cdlBillList.put("Unit2", reader.getElementText());
                        }
                    }

                    // CdlBill/CdlBillDocs/CdlBillDoc
                    if ("CdlBillDoc".equals(reader.getLocalName())) {
                        cdlBillDoc = new LinkedHashMap<>();
                    }
                    {
                        // DocCode
                        if ("DocCode".equals(reader.getLocalName())) {
                            cdlBillDoc.put("DocCode", reader.getElementText());
                        }
                        // CertNo
                        if ("CertNo".equals(reader.getLocalName())) {
                            cdlBillDoc.put("CertNo", reader.getElementText());
                        }
                    }
                }

                if (event == XMLStreamReader.END_ELEMENT) {
                    // CdlBill/CdlBillHead
                    if ("CdlBillHead".equals(reader.getLocalName())) {

                    }

                    // CdlBill/CdlBillLists/CdlBillList
                    if ("CdlBillList".equals(reader.getLocalName())) {
                        cdlBillLists.add(cdlBillList);
                    }

                    // CdlBill/CdlBillDocs/CdlBillDoc
                    if ("CdlBillDoc".equals(reader.getLocalName())) {
                        cdlBillDocs.add(cdlBillDoc);
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

        cdl.put("CdlBillHead", cdlBillHead);
        cdl.put("CdlBillLists", cdlBillLists);
        cdl.put("CdlBillDocs", cdlBillDocs);
        System.out.println(cdlBillLists.size() + " " + cdlBillDocs.size());
        persistentRepositoryService.save(cdl);

    }
}
