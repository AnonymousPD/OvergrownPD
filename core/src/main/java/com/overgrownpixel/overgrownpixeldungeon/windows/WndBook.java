/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Overgrown Pixel Dungeon
 * Copyright (C) 2018-2019 Anon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.overgrownpixel.overgrownpixeldungeon.windows;

import com.overgrownpixel.overgrownpixeldungeon.OGPDSettings;
import com.overgrownpixel.overgrownpixeldungeon.books.Book;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.PixelScene;
import com.overgrownpixel.overgrownpixeldungeon.ui.RenderedTextMultiline;
import com.overgrownpixel.overgrownpixeldungeon.ui.Window;

public class WndBook extends Window {

	//only one wnditem can appear at a time
	private static WndBook INSTANCE;

	private static final float GAP	= 2;

	private static final int WIDTH_MIN = 120;
	private static final int WIDTH_MAX = 220;


	public WndBook(final Book book ) {
		
		super();
		
		if( INSTANCE != null ){
			INSTANCE.hide();
		}
		INSTANCE = this;

		int width = WIDTH_MIN;
		
		RenderedTextMultiline info = PixelScene.renderMultiline(
                                Messages.get(this, "written_by", book.author) +"\n"+
                                Messages.get(this, "age", book.age) +"\n\n"+
                                book.desc, 6 );
		info.maxWidth(width);
		
		//info box can go out of the screen on landscape, so widen it
		while (OGPDSettings.landscape()
				&& info.height() > 100
				&& width < WIDTH_MAX){
			width += 20;
			info.maxWidth(width);
		}
		
		IconTitle titlebar = new IconTitle( book );
		titlebar.setRect( 0, 0, width, 0 );
		add( titlebar );
		
		info.setPos(titlebar.left(), titlebar.bottom() + GAP);
		add( info );

		float y = info.top() + info.height() + GAP;
		
		resize( width, (int) y);
	}
	
	@Override
	public void hide() {
		super.hide();
		if (INSTANCE == this){
			INSTANCE = null;
		}
	}
}
