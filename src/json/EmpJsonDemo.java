package json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * These jars are required to execute this program
 * C:\Users\Chari\.m2\repository\com\fasterxml\jackson\core\jackson-core\2.6.3\jackson-core-2.6.3.jar
 *C:\Users\Chari\.m2\repository\com\fasterxml\jackson\core\jackson-annotations\2.6.0\jackson-annotations-2.6.0.jar
 *C:\Users\Chari\.m2\repository\com\fasterxml\jackson\core\jackson-databind\2.6.3\jackson-databind-2.6.3.jar
 */


public class EmpJsonDemo {

	/**
	 * @param args
	 * @throws Exception
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, Exception {

		ObjectMapper mapper = new ObjectMapper();

		EmpJson empJson = createDummyEmp();
		AddressWithListOfEmp addWithEmpList = createAddWtihEmpList();

		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		String jsonInString = "[{\'name\' : \'mkyong\',\'id\':10,\'desiggnation\':\'SE\'}]";
		
		List<EmpJson> emp = mapper.readValue(jsonInString, new TypeReference<List<EmpJson>>() {});
		// above and below are same
		List<EmpJson> empJsonString = fromJSON(new TypeReference<List<EmpJson>>() {}, jsonInString);
		
		for (EmpJson object : emp) {
			System.out.println(object.getName());
			System.out.println(object.getDesignation());
		}

		System.out.println(emp);
		String empStr = mapper.writeValueAsString(empJson);
		System.out.println(empStr);
		String addstr = mapper.writeValueAsString(addWithEmpList);
		System.out.println(addstr);

		AddressWithListOfEmp addemp = mapper.readValue(addstr,
				AddressWithListOfEmp.class);
		System.out.println(addemp.getEmpJsonList().size());

	}

	private static AddressWithListOfEmp createAddWtihEmpList() {
		AddressWithListOfEmp add = new AddressWithListOfEmp();
		List<EmpJson> empJsonList = new ArrayList<EmpJson>();
		empJsonList.add(createEmpJsonObj(1, "raju", "SE"));
		empJsonList.add(createEmpJsonObj(2, "anu", "PA"));
		empJsonList.add(createEmpJsonObj(3, "siri", "TL"));
		add.setStreet("taramani");
		add.setState("Chn");
		add.setEmpJsonList(empJsonList);
		return add;
	}

	private static EmpJson createEmpJsonObj(int id, String name, String desig) {
		EmpJson empJson = new EmpJson();
		empJson.setId(id);
		empJson.setName(name);
		empJson.setDesignation(desig);
		return empJson;
	}

	private static EmpJson createDummyEmp() {
		EmpJson emp = new EmpJson();
		emp.setId(1);
		emp.setName("raju");
		emp.setDesignation("SE");
		return emp;
	}

	public static <T> T fromJSON(final TypeReference<T> type,
			final String jsonPacket) {
		T data = null;

		try {
			data = new ObjectMapper().readValue(jsonPacket, type);
		} catch (Exception e) {
			// Handle the problem
		}
		return data;
	}

}
