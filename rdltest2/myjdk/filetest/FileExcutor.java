package filetest;

import java.io.File;

public interface FileExcutor<T>{
	
	public void accept( File f );
	
	public void finishCurrentDir( File dir );
	
	public T getResult();
}