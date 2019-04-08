/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package aspect.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldService {

	@Value("${name:World}")
	private String name;

	int i = 0;
	public String getHelloMessage( String name ) {
//		if( "exception".equals(name) ){
//			throw new RuntimeException("有异常了！！");
//		}
		String s =  "Hello " + name + "test!" + (i++);
		System.out.println("invoke" + s );
		return s;
	}

	@AnnotationTag
	public String getHelloMessage() {
		if( "exception".equals(name) ){
			throw new RuntimeException("有异常了！！");
		}
		i++;
		String s =  "Hello " + name + "test!" + i;
//		return "Hello " + name + "test!";
		System.out.println("=============================invoke" + s );
		return s ;
	}
}
