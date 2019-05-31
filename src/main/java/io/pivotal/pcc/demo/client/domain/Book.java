package io.pivotal.pcc.demo.client.domain;

import java.io.Serializable;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.expiration.TimeToLiveExpiration;
import org.springframework.data.gemfire.mapping.annotation.Region;

@Entity
@Region("Books")
@TimeToLiveExpiration(timeout = "60", action = "DESTROY")
@Table(name = "books")


@EqualsAndHashCode(of = "isbn")
@ToString
@RequiredArgsConstructor(staticName = "isbn")
@AllArgsConstructor

public class Book implements Serializable {

	@Id
	@javax.persistence.Id
	private String isbn;

	@Getter
	private String title;
	@Getter
	private String author;
	@Getter
	@Setter
	private String genre;

}
