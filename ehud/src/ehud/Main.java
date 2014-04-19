package ehud;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		World world = new World();
		world.initialize();
		Plot p = new Plot();
		while(world.hasNext()){
			world.step();
			p.setStepNumber(world.getCurrentStep());
			p.saveImageToFile(world);
		}
	}

}
