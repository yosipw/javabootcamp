package bootcamp.screen;

/**
 * @author Yosua_S
 *
 */
public abstract class AbstractScreen implements InterfaceScreenAction {
	
	protected EnumScreen toPage;

	@Override
	public EnumScreen changePage() {
		return toPage;
	}

}
