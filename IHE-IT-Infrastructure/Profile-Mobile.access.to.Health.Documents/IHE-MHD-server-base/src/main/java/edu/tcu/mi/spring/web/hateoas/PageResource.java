package edu.tcu.mi.spring.web.hateoas;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class PageResource<T> extends ResourceSupport {


	public PageResource(Page<T> page, String pageParam, String sizeParam) {
		super();
		number = page.getNumber();
		size = page.getSize();
		numberOfElements = page.getNumberOfElements();
		content = page.getContent();
		sort = page.getSort();
		firstPage = page.isFirst();
		lastPage = page.isLast();
		totalPages = page.getTotalPages();
		totalElements = page.getTotalElements();
		
		addPreviousLink(page, pageParam, sizeParam);
		addNextLink(page, pageParam, sizeParam);
		addFirstLink(page, pageParam, sizeParam);
		addLastLink(page, pageParam, sizeParam);
		addSelfLink(page, pageParam, sizeParam);
	}


	private Link buildPageLink(String pageParam, int page, String sizeParam, int size, String rel) {
		String path = 
				createBuilder()
				.queryParam(pageParam, page)
				.queryParam(sizeParam, size)
				.build()
				.toString();
		Link link = new Link(path, rel);
		return link;
	}
	
	private void addPreviousLink(Page<T> page, String pageParam, String sizeParam) {
		if (page.hasPrevious()) {
			Link link = buildPageLink(pageParam, page.getNumber() - 1, sizeParam, page.getSize(), Link.REL_PREVIOUS);
			add(link);
		}
	}

	private void addNextLink(Page<T> page, String pageParam, String sizeParam) {
		if (page.hasNext()) {
			Link link = buildPageLink(pageParam, page.getNumber() + 1, sizeParam, page.getSize(), Link.REL_NEXT);
			add(link);
		}
	}

	private void addFirstLink(Page<T> page, String pageParam, String sizeParam) {
		if (page.isFirst()) {
			Link link = buildPageLink(pageParam, 0, sizeParam, page.getSize(), Link.REL_FIRST);
			add(link);
		}
	}

	private void addLastLink(Page<T> page, String pageParam, String sizeParam) {
		if (page.isLast()) {
			Link link = buildPageLink(pageParam, page.getTotalPages(), sizeParam, page.getSize(), Link.REL_LAST);
			add(link);
		}
	}

	private void addSelfLink(Page<T> page, String pageParam, String sizeParam) {
		Link link = buildPageLink(pageParam, page.getNumber(), sizeParam, page.getSize(), Link.REL_SELF);
		add(link);
	}

	private ServletUriComponentsBuilder createBuilder() {
		return ServletUriComponentsBuilder.fromCurrentRequestUri();
	}

	public int getNumber() {
		return number;
	}

	public int getSize() {
		return size;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public List<T> getContent() {
		return content;
	}

	public Sort getSort() {
		return sort;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	private final List<T> content;
	private final int number;
	private final int size;
	private final int numberOfElements;
	private final Sort sort;
	private final boolean firstPage;
	private final boolean lastPage;
	private final int totalPages;
	private final long totalElements;
}
