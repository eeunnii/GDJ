package ex02_writer;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.tools.classfile.Annotation.element_value;
import com.sun.tools.sjavac.Transformer;

public class XMLWriter {

	public static void main(String[] args) {
		
		
		// XML
		// 1. Extensible Msrkup Language
		// 2. 확장 마크업 언어 
		// 3. 표준 마크업 언어인 HTML의 확장버전
		// 4. 정해진 태그 외 사용자 정의 태그 사용
		/* 
		 * <product>
		 * 			<number>100</number>
		 * 			<name> 새우깡 <\name>
		 * 			<price>1500</price>
		 * </product>
		 * <product>
		 * 			<number>100</number>
		 * 			<name> 양파링 <\name>
		 * 			<price>1500</price>
		 * </product>
		 * <product>
		 * 			<number>100</number>
		 * 			<name> 홈런볼 <\name>
		 * 			<price>1500</price>
		 * </product>
		 */
			try {
				
				
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document document = builder.newDocument();
				document.setXmlStandalone(true);
				
				//태그생성
				
				List<String> product1 = Arrays.asList("100", "새우깡", "1500");
				List<String> product2 = Arrays.asList("101", "양파링", "2000");
				List<String> product3 = Arrays.asList("102", "홈런볼", "3000");
				
				List<List<String>> list = Arrays.asList(product1, product2, product3);
			
			
				for(List<String>line : list) {
					Element purduct = document.createElement("product");
					Element number = document.createElement("number");
					number.
					Element name = document.createElement("name");
					Element price = document.createElement("price");
					//태그 배치 
					document.appendChild(product);
					document.appendChild(number);
					document.appendChild(name);
					document.appendChild(price);
				}
				
				//XML 생성
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty("encoding", "UTF-8");
				transformer.setOutputProperty("indent", "true");
				
				Source source = new DOMSource()document;
				File file = new File(C:\\storge", "procuct.xml");	"
				StreamResult result = new StreamResult()	
				
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
							
				}
			
			
			
			
			}
		
		
		
		
		
		
	}

}
