package com.ch.nike.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ch.nike.dto.Category;
import com.ch.nike.dto.Product;
import com.ch.nike.dto.ProductDetail;
import com.ch.nike.dto.ProductFeature;
import com.ch.nike.dto.ProductPhoto;
import com.ch.nike.service.ProductDetailService;
import com.ch.nike.service.ProductFeatureService;
import com.ch.nike.service.ProductPhotoService;
import com.ch.nike.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService ps;
	@Autowired
	private ProductPhotoService pps;
	@Autowired
	private ProductDetailService pds;
	@Autowired
	private ProductFeatureService pfs;
	
	@RequestMapping("/") //메인 최하단 하단 신발 리스트 by창률 // 로그아웃 위한 세션작업 by수인
	public String main(Model model, HttpSession session) {
		List<Product> product = ps.list();
		model.addAttribute("product", product);
		return "main";
	}
	
	@RequestMapping("/productList.do") // 상품리스트 상품 리스트불러오기 (대분류,소분류), 상품수체크 by창률
	public String productList(Model model, Category category ) {
		List<Product> product = ps.productlist(category);
		Product product2 = ps.count(category);
		List<Product> product3 = ps.color(category); 
		Product product4 = ps.colorcount(category);
		model.addAttribute("product",product);
		model.addAttribute("category",category);
		model.addAttribute("productcount",product2);
		model.addAttribute("color",product3);
		model.addAttribute("colorcount",product4);
		return "product/productList";
	}
	
	@RequestMapping("/product/product.do")	// 등록된 상품 불러오기 by선희
	public String product(Model model) {
		List<Product> list = ps.selectProduct();
		model.addAttribute("list", list);
		return "product/product";
	}

	@RequestMapping("/product/productDetail.do")	// 상품 상세 페이지 by선희
	public String colorChange(String color, int productNo, Model model) {
		Product product = ps.selectProductOne(productNo);
		List<ProductFeature> pfeature = pfs.selectFeature(productNo);
		List<ProductDetail> anotherColor = pps.colorChange(color, productNo);
		if (anotherColor.size() != 0) {
			model.addAttribute("anotherColor", anotherColor);
		} else {
			String msg = "준비중입니다.";
			model.addAttribute("msg", msg);
		}
		List<ProductDetail> colors = null;
		List<ProductDetail> sizes = null;
		if (product != null) {
			colors = pds.color(productNo);
			sizes = pds.size(productNo);
		}
		model.addAttribute("color", color);
		model.addAttribute("product", product);
		model.addAttribute("pf", pfeature);
		model.addAttribute("colors", colors);
		model.addAttribute("sizes", sizes);
		return "product/productDetail";
	}
}
