package aspect.invoke;

public class InovkeServiceImpl implements InovkeServce{

	@Override
	public String encode(String s) {
		return "<<<<<<<<<<<<<<<"+s+">>>>>>>>>>>>>>>>";
	}

	@Override
	public void print(String s) {
		System.out.println("================" + s);
	}
}
