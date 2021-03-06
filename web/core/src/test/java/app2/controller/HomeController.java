/*
 * Copyright 2014 the original author or authors.
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
package app2.controller;

import app.models.products.Product;
import leap.web.Results;

public class HomeController {

	public void index() {
		System.out.println("");
	}
	public void index2() {
		Results.renderView("/index.jsp");
	}
	public Product getProduct() {
		Product product = new Product();
		
		product.setId(100);
		product.setTitle("Iphone6");
		
		return product;
	}
}
