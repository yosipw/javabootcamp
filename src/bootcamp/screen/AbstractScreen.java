package screen;

public abstract class AbstractScreen implements InterfaceScreen {
	
	protected EnumScreen toPage;

	@Override
	public EnumScreen changePage() {
		return toPage;
	}

}
