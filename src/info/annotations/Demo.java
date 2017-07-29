package info.annotations;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestAnnotationParser tap = new TestAnnotationParser();
		tap.parse(Annotated.class);
		System.out.println("finished");
	}

}
