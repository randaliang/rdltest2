package filetest;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class FileFilter {

	
	public static Set<String> getClassFileNames( File dir ){
		Set<String> set = new HashSet<String>();
		if( dir.isDirectory() ) {
			File[] files = dir.listFiles();
			for( File f :files ) {
				if( f.isDirectory() ) {
					set.addAll( getClassFileNames(f));
				}else {
					if( f.getName().endsWith(".class")) {
						set.add(f.getName().replace(".class", ""));
					}
				}
				
			}
		}
		return set;
	}
	
	public static void removeFiles( File dir, Set<String> set ) {
		
		if( dir.isDirectory() ) {
			File[] files = dir.listFiles();
			for( File f :files ) {
				if( f.isDirectory() ) {
					removeFiles(f,set);
				}else {
					if(  !f.getName().endsWith(".java") ) {
						f.delete();
					}else {
						String name = f.getName().replace(".java", "");
						if(!set.contains(name)) {
							f.delete();
						}
					}
				}
				
			}
			if(dir.list().length == 0) {
				dir.delete();
			}
		}
	}
	
	public static void main( String args[] ) {
		 File f  = new File("D:\\temp\\广州银行\\比对\\广州银行增量包-2019年6月26日");
//		 File[] files = f.listFiles((x,y)->{ return true ;});
		 Set<String> set = getClassFileNames(f);
		 File old = new File("D:\\temp\\广州银行\\比对\\旧的");
		 removeFiles(old,set);
		 File newfile  = new File("D:\\temp\\广州银行\\比对\\新的");
		 removeFiles(newfile,set);
	}


	
}
