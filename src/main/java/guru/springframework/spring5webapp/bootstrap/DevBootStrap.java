package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootStrap(AuthorRepository authorRepository,BookRepository bookRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private void initData(){
        //Eric
        Author eric = new Author("Eric","Evans");
        Book book1 = new Book("Domain Driven Design","1234","Harper Collins");
        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(book1);

        //Rod
        Author rod = new Author("Rod","Johnson");
        Book noEJB = new Book("J2EE Development without EJB","234444","Worx");
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);


    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
