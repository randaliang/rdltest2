package filetest;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class FindFileOperator implements FileExcutor<Set> {

	Set<String> set = new HashSet<String>();
	@Override
	public void accept(File f) {
		if( f.getName().endsWith(".class") ) {
			set.add( f.getName().replace(".class", "") );
		}
	}

	@Override
	public void finishCurrentDir(File dir) {
		
	}

	@Override
	public Set getResult() {
		return set;
	}

}
