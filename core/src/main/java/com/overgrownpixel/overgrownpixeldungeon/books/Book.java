package com.overgrownpixel.overgrownpixeldungeon.books;

import com.overgrownpixel.overgrownpixeldungeon.books.lorebooks.BookAncientAlchemistWar;
import com.overgrownpixel.overgrownpixeldungeon.books.lorebooks.BookHowToConsumeTheDead;
import com.overgrownpixel.overgrownpixeldungeon.books.lorebooks.BookImperium;
import com.overgrownpixel.overgrownpixeldungeon.books.lorebooks.BookJosephsLastThoughts;
import com.overgrownpixel.overgrownpixeldungeon.books.lorebooks.BookMorningOfTheMagicians;
import com.overgrownpixel.overgrownpixeldungeon.books.lorebooks.BookMysterieOfTheGrail;
import com.overgrownpixel.overgrownpixeldungeon.books.lorebooks.BookOfTheCreator;
import com.overgrownpixel.overgrownpixeldungeon.books.lorebooks.BookOnTheRogue;
import com.overgrownpixel.overgrownpixeldungeon.books.lorebooks.BookProAsHeckGuideToSniper;
import com.overgrownpixel.overgrownpixeldungeon.books.lorebooks.BookSproutingNature;
import com.overgrownpixel.overgrownpixeldungeon.books.lorebooks.BookTheGoldenArrow;
import com.overgrownpixel.overgrownpixeldungeon.books.lorebooks.BookThisTimeTheUniverse;
import com.overgrownpixel.overgrownpixeldungeon.books.lorebooks.BookYendorianDwarves;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSprite;
import com.watabou.noosa.particles.Emitter;

import java.util.ArrayList;

public class Book {

    public String title = Messages.get(this, "title");
    public String author = Messages.get(this, "author");
    public Integer age;
    public String desc = Messages.get(this, "desc");
    public Integer icon = null;

    public Emitter emitter() { return null; }

    public ItemSprite.Glowing glowing() {
        return null;
    }

    public static final ArrayList<Class<?extends Book>> allBooks = new ArrayList<>();
    static{
        allBooks.add(BookAncientAlchemistWar.class);
        allBooks.add(BookMorningOfTheMagicians.class);
        allBooks.add(BookThisTimeTheUniverse.class);
        allBooks.add(BookTheGoldenArrow.class);
        allBooks.add(BookMysterieOfTheGrail.class);
        allBooks.add(BookImperium.class);
        allBooks.add(BookHowToConsumeTheDead.class);
        allBooks.add(BookProAsHeckGuideToSniper.class);
        allBooks.add(BookYendorianDwarves.class);
        allBooks.add(BookOfTheCreator.class);
        allBooks.add(BookOnTheRogue.class);
        allBooks.add(BookSproutingNature.class);
        allBooks.add(BookJosephsLastThoughts.class);
    }
}
