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

package com.overgrownpixel.overgrownpixeldungeon.sprites;

import com.overgrownpixel.overgrownpixeldungeon.Assets;
import com.overgrownpixel.overgrownpixeldungeon.Dungeon;
import com.overgrownpixel.overgrownpixeldungeon.R;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.effects.Butter;
import com.overgrownpixel.overgrownpixeldungeon.effects.CocosHalo;
import com.overgrownpixel.overgrownpixeldungeon.effects.DarkBlock;
import com.overgrownpixel.overgrownpixeldungeon.effects.EmoIcon;
import com.overgrownpixel.overgrownpixeldungeon.effects.EyeHalo;
import com.overgrownpixel.overgrownpixeldungeon.effects.FloatingText;
import com.overgrownpixel.overgrownpixeldungeon.effects.IceBlock;
import com.overgrownpixel.overgrownpixeldungeon.effects.IronSkin;
import com.overgrownpixel.overgrownpixeldungeon.effects.RoseHalo;
import com.overgrownpixel.overgrownpixeldungeon.effects.Shadow;
import com.overgrownpixel.overgrownpixeldungeon.effects.ShieldHalo;
import com.overgrownpixel.overgrownpixeldungeon.effects.ShieldedHalo;
import com.overgrownpixel.overgrownpixeldungeon.effects.Speck;
import com.overgrownpixel.overgrownpixeldungeon.effects.Splash;
import com.overgrownpixel.overgrownpixeldungeon.effects.TorchHalo;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.FlameParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.HalomethaneFlameParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.HellFlameParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.ShadowParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.SnowParticle;
import com.overgrownpixel.overgrownpixeldungeon.effects.particles.SoulFlameParticle;
import com.overgrownpixel.overgrownpixeldungeon.messages.Messages;
import com.overgrownpixel.overgrownpixeldungeon.scenes.GameScene;
import com.overgrownpixel.overgrownpixeldungeon.scenes.PixelScene;
import com.overgrownpixel.overgrownpixeldungeon.tiles.DungeonTilemap;
import com.overgrownpixel.overgrownpixeldungeon.ui.CharHealthIndicator;
import com.watabou.glwrap.Matrix;
import com.watabou.glwrap.Vertexbuffer;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.MovieClip;
import com.watabou.noosa.NoosaScript;
import com.watabou.noosa.Visual;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.particles.Emitter;
import com.watabou.noosa.tweeners.AlphaTweener;
import com.watabou.noosa.tweeners.PosTweener;
import com.watabou.noosa.tweeners.Tweener;
import com.watabou.utils.Callback;
import com.watabou.utils.PointF;
import com.watabou.utils.Random;

public class CharSprite extends MovieClip implements Tweener.Listener, MovieClip.Listener {
	
	// Color constants for floating text
	public static final int DEFAULT		= Game.instance.getResources().getInteger(R.integer.floatingtextdefault);
	public static final int POSITIVE	= Game.instance.getResources().getInteger(R.integer.floatingtextpositive);
	public static final int NEGATIVE	= Game.instance.getResources().getInteger(R.integer.floatingtextnegative);
	public static final int WARNING		= Game.instance.getResources().getInteger(R.integer.floatingtextwarning);
	public static final int NEUTRAL		= Game.instance.getResources().getInteger(R.integer.floatingtextneutral);
	
	private static final float MOVE_INTERVAL	= 0.1f;
	private static final float FLASH_INTERVAL	= 0.05f;

	//the amount the sprite is raised from flat when viewed in a raised perspective
	protected float perspectiveRaise    = 6 / 16f; //6 pixels

	//the width and height of the shadow are a percentage of sprite size
	//offset is the number of pixels the shadow is moved down or up (handy for some animations)
	protected boolean renderShadow  = false;
	protected float shadowWidth     = 1.2f;
	protected float shadowHeight    = 0.25f;
	protected float shadowOffset    = 0.25f;

	public enum State {
		BURNING, LEVITATING, INVISIBLE, PARALYSED,
        FROZEN, ILLUMINATED, CHILLED, DARKENED,
        MARKED, HEALING, SHIELDED, HALOMETHANEBURNING,
        ROSESHIELDED, SHADOW, COCOSHIELDED, BUTTER,
        SOULFIRE, EYING, SHIELD, HELLBURNING, IRONSKIN
	}
	
	protected Animation idle;
	protected Animation run;
	protected Animation attack;
	protected Animation operate;
	protected Animation zap;
	protected Animation die;
	
	protected Callback animCallback;
	
	protected Tweener motion;
	
	protected Emitter burning;
    protected Emitter haloburning;
	protected Emitter chilled;
	protected Emitter marked;
	protected Emitter levitation;
	protected Emitter healing;
    protected Emitter soulfire;
    protected Emitter hellburning;
	
	protected IceBlock iceBlock;
    protected IronSkin ironSkin;
    protected EyeHalo eye;
    protected Shadow shadow;
	protected DarkBlock darkBlock;
	protected TorchHalo light;
	protected ShieldHalo shield;
    protected CocosHalo cocosshield;
    protected ShieldedHalo shieldHalo;
    protected RoseHalo roseshield;
	protected AlphaTweener invisible;
    protected Butter butter;
	
	protected EmoIcon emo;
	protected CharHealthIndicator health;

	private Tweener jumpTweener;
	private Callback jumpCallback;

	protected float flashTime = 0;
	
	protected boolean sleeping = false;

	public Char ch;

	//used to prevent the actor associated with this sprite from acting until movement completes
	public volatile boolean isMoving = false;
	
	public CharSprite() {
		super();
		listener = this;
	}
	
	@Override
	public void play(Animation anim) {
		//Shouldn't interrupt the dieing animation
		if (curAnim == null || curAnim != die) {
			super.play(anim);
		}
	}
	
	public void link(Char ch ) {
		this.ch = ch;
		ch.sprite = this;
		
		place( ch.pos );
		turnTo( ch.pos, Random.Int( Dungeon.level.length() ) );
		renderShadow = true;
		
		if (ch != Dungeon.hero) {
			if (health == null) {
				health = new CharHealthIndicator(ch);
			} else {
				health.target(ch);
			}
		}

		ch.updateSpriteState();
	}
	
	public PointF worldToCamera( int cell ) {
		
		final int csize = DungeonTilemap.SIZE;
		
		return new PointF(
			PixelScene.align(Camera.main, ((cell % Dungeon.level.width()) + 0.5f) * csize - width * 0.5f),
			PixelScene.align(Camera.main, ((cell / Dungeon.level.width()) + 1.0f) * csize - height - csize * perspectiveRaise)
		);
	}
	
	public void place( int cell ) {
		point( worldToCamera( cell ) );
	}
	
	public void showStatus( int color, String text, Object... args ) {
		if (visible) {
			if (args.length > 0) {
				text = Messages.format( text, args );
			}
			if (ch != null) {
				FloatingText.show( x + width * 0.5f, y, ch.pos, text, color );
			} else {
				FloatingText.show( x + width * 0.5f, y, text, color );
			}
		}
	}
	
	public void idle() {
		play(idle);
	}
	
	public void move( int from, int to ) {
		turnTo( from , to );

		play( run );
		
		motion = new PosTweener( this, worldToCamera( to ), MOVE_INTERVAL );
		motion.listener = this;
		parent.add( motion );

		isMoving = true;
		
		if (visible && Dungeon.level.water[from] && !ch.flying) {
			GameScene.ripple( from );
		}

	}
	
	public void interruptMotion() {
		if (motion != null) {
			motion.stop(false);
		}
	}
	
	public void attack( int cell ) {
		turnTo( ch.pos, cell );
		play( attack );
	}
	
	public void attack( int cell, Callback callback ) {
		animCallback = callback;
		turnTo( ch.pos, cell );
		play( attack );
	}
	
	public void operate( int cell ) {
		turnTo( ch.pos, cell );
		play( operate );
	}
	
	public void operate( int cell, Callback callback ) {
		animCallback = callback;
		turnTo( ch.pos, cell );
		play( operate );
	}
	
	public void zap( int cell ) {
		turnTo( ch.pos, cell );
		play( zap );
	}
	
	public void zap( int cell, Callback callback ) {
		animCallback = callback;
		zap( cell );
	}
	
	public void turnTo( int from, int to ) {
		int fx = from % Dungeon.level.width();
		int tx = to % Dungeon.level.width();
		if (tx > fx) {
			flipHorizontal = false;
		} else if (tx < fx) {
			flipHorizontal = true;
		}
	}

	public void jump( int from, int to, Callback callback ) {
		jumpCallback = callback;

		int distance = Dungeon.level.distance( from, to );
		jumpTweener = new JumpTweener( this, worldToCamera( to ), distance * 4, distance * 0.1f );
		jumpTweener.listener = this;
		parent.add( jumpTweener );

		turnTo( from, to );
	}

	public void die() {
		sleeping = false;
		play( die );
		
		if (emo != null) {
			emo.killAndErase();
		}
		
		if (health != null){
			health.killAndErase();
		}
	}
	
	public Emitter emitter() {
		Emitter emitter = GameScene.emitter();
		emitter.pos( this );
		return emitter;
	}
	
	public Emitter centerEmitter() {
		Emitter emitter = GameScene.emitter();
		emitter.pos( center() );
		return emitter;
	}
	
	public Emitter bottomEmitter() {
		Emitter emitter = GameScene.emitter();
		emitter.pos( x, y + height, width, 0 );
		return emitter;
	}
	
	public void burst( final int color, int n ) {
		if (visible) {
			Splash.at( center(), color, n );
		}
	}
	
	public void bloodBurstA( PointF from, int damage ) {
		if (visible) {
			PointF c = center();
			int n = (int)Math.min( 9 * Math.sqrt( (double)damage / ch.HT ), 9 );
			Splash.at( c, PointF.angle( from, c ), 3.1415926f / 2, blood(), n );
		}
	}

	public int blood() {
		return Game.instance.getResources().getInteger(R.integer.blood);
	}
	
	public void flash() {
		ra = ba = ga = 1f;
		flashTime = FLASH_INTERVAL;
	}
	
	public void add( State state ) {
		switch (state) {
			case BURNING:
                burning = emitter();
                burning.pour( FlameParticle.FACTORY, 0.06f );
                if (visible) {
                    Sample.INSTANCE.play( Assets.SND_BURNING );
                }
                break;
            case HELLBURNING:
                burning = emitter();
                burning.pour( HellFlameParticle.FACTORY, 0.06f );
                if (visible) {
                    Sample.INSTANCE.play( Assets.SND_BURNING );
                }
                break;
            case EYING:
                GameScene.effect( eye = new EyeHalo( this ));
                break;
            case SOULFIRE:
                soulfire = emitter();
                soulfire.pour( SoulFlameParticle.FACTORY, 0.06f );
                if (visible) {
                    Sample.INSTANCE.play( Assets.SND_BURNING );
                }
                break;
            case HALOMETHANEBURNING:
                haloburning = emitter();
                haloburning.pour( HalomethaneFlameParticle.FACTORY, 0.04f );
                if (visible) {
                    Sample.INSTANCE.play( Assets.SND_BURNING );
                }
                break;
			case LEVITATING:
				levitation = emitter();
				levitation.pour( Speck.factory( Speck.JET ), 0.02f );
				break;
			case INVISIBLE:
                if (invisible != null) {
                    invisible.killAndErase();
                }
                invisible = new AlphaTweener( this, 0.4f, 0.4f );
                if (parent != null){
                    parent.add(invisible);
                } else
                    alpha( 0.4f );
                break;
            case SHADOW:
                if (invisible != null) {
                    invisible.killAndErase();
                }
                invisible = new AlphaTweener( this, 0.6f, 0.6f );
                if (parent != null){
                    parent.add(invisible);
                } else
                    alpha( 0.6f );
                shadow = Shadow.cloak( this );
                break;
			case PARALYSED:
				paused = true;
				break;
			case FROZEN:
				iceBlock = IceBlock.freeze( this );
				paused = true;
				break;
            case IRONSKIN:
                ironSkin = IronSkin.ironUp( this );
                break;
            case BUTTER:
                butter = Butter.butter( this );
                break;
			case ILLUMINATED:
				GameScene.effect( light = new TorchHalo( this ) );
				break;
			case CHILLED:
				chilled = emitter();
				chilled.pour(SnowParticle.FACTORY, 0.1f);
				break;
			case DARKENED:
				darkBlock = DarkBlock.darken( this );
				break;
			case MARKED:
				marked = emitter();
				marked.pour(ShadowParticle.UP, 0.1f);
				break;
			case HEALING:
				healing = emitter();
				healing.pour(Speck.factory(Speck.HEALING), 0.5f);
				break;
			case SHIELDED:
				GameScene.effect( shield = new ShieldHalo( this ));
				break;
            case COCOSHIELDED:
                GameScene.effect( cocosshield = new CocosHalo( this ));
            case SHIELD:
                GameScene.effect( shieldHalo = new ShieldedHalo( this ));
                break;
            case ROSESHIELDED:
                GameScene.effect( roseshield = new RoseHalo( this ));
                break;
		}
	}
	
	public void remove( State state ) {
		switch (state) {
			case BURNING:
				if (burning != null) {
					burning.on = false;
					burning = null;
				}
				break;
            case HELLBURNING:
                if (hellburning != null) {
                    hellburning.on = false;
                    hellburning = null;
                }
                break;
            case SOULFIRE:
            if (soulfire != null) {
                soulfire.on = false;
                soulfire = null;
            }
            break;
            case HALOMETHANEBURNING:
                if (haloburning != null) {
                    haloburning.on = false;
                    haloburning = null;
                }
                break;
			case LEVITATING:
				if (levitation != null) {
					levitation.on = false;
					levitation = null;
				}
				break;
			case INVISIBLE:
				if (invisible != null) {
					invisible.killAndErase();
					invisible = null;
				}
				alpha( 1f );
				break;
            case SHADOW:
                if (invisible != null) {
                    invisible.killAndErase();
                    invisible = null;
                }
                alpha( 1f );
                if (shadow != null) {
                    shadow.dissapear();
                    shadow = null;
                }
                break;
			case PARALYSED:
				paused = false;
				break;
			case FROZEN:
                if (iceBlock != null) {
                    iceBlock.melt();
                    iceBlock = null;
                }
                paused = false;
                break;
            case IRONSKIN:
            if (ironSkin != null) {
                ironSkin.ironDown();
                ironSkin = null;
            }
            break;
            case EYING:
                if (eye != null){
                    eye.putOut();
                }
                break;
            case BUTTER:
                if (butter != null) {
                    butter.dissapear();
                    butter = null;
                }
                break;
			case ILLUMINATED:
				if (light != null) {
					light.putOut();
				}
				break;
			case CHILLED:
				if (chilled != null){
					chilled.on = false;
					chilled = null;
				}
				break;
			case DARKENED:
				if (darkBlock != null) {
					darkBlock.lighten();
					darkBlock = null;
				}
				break;
			case MARKED:
				if (marked != null){
					marked.on = false;
					marked = null;
				}
				break;
			case HEALING:
				if (healing != null){
					healing.on = false;
					healing = null;
				}
				break;
			case SHIELDED:
				if (shield != null){
					shield.putOut();
				}
				break;
            case COCOSHIELDED:
                if (cocosshield != null){
                    cocosshield.putOut();
                }
                break;
            case SHIELD:
                if (shieldHalo != null){
                    shieldHalo.putOut();
                }
                break;
            case ROSESHIELDED:
                if (roseshield != null){
                    roseshield.putOut();
                }
                break;
		}
	}
	
	@Override
	//syncronized due to EmoIcon handling
	public synchronized void update() {
		
		super.update();
		
		if (paused && listener != null) {
			listener.onComplete( curAnim );
		}
		
		if (flashTime > 0 && (flashTime -= Game.elapsed) <= 0) {
			resetColor();
		}
		
		if (burning != null) {
			burning.visible = visible;
		}
        if (haloburning != null) {
            haloburning.visible = visible;
        }
		if (levitation != null) {
			levitation.visible = visible;
		}
		if (iceBlock != null) {
			iceBlock.visible = visible;
		}
        if (shadow != null) {
            shadow.visible = visible;
        }
		if (chilled != null) {
			chilled.visible = visible;
		}
		if (marked != null) {
			marked.visible = visible;
		}
		if (sleeping) {
			showSleep();
		} else {
			hideSleep();
		}
		if (emo != null && emo.alive) {
			emo.visible = visible;
		}
	}
	
	@Override
	public void resetColor() {
		super.resetColor();
		if (invisible != null){
			alpha(0.4f);
		}
	}
	
	public synchronized void showSleep() {
		if (emo instanceof EmoIcon.Sleep) {
			
		} else {
			if (emo != null) {
				emo.killAndErase();
			}
			emo = new EmoIcon.Sleep( this );
			emo.visible = visible;
		}
		idle();
	}
	
	public synchronized void hideSleep() {
		if (emo instanceof EmoIcon.Sleep) {
			emo.killAndErase();
			emo = null;
		}
	}
	
	public synchronized void showAlert() {
		if (emo instanceof EmoIcon.Alert) {
			
		} else {
			if (emo != null) {
				emo.killAndErase();
			}
			emo = new EmoIcon.Alert( this );
			emo.visible = visible;
		}
	}
	
	public synchronized void hideAlert() {
		if (emo instanceof EmoIcon.Alert) {
			emo.killAndErase();
			emo = null;
		}
	}
	
	public synchronized void showLost() {
		if (emo instanceof EmoIcon.Lost) {
		
		} else {
			if (emo != null) {
				emo.killAndErase();
			}
			emo = new EmoIcon.Lost( this );
			emo.visible = visible;
		}
	}
	
	public synchronized void hideLost() {
		if (emo instanceof EmoIcon.Lost) {
			emo.killAndErase();
			emo = null;
		}
	}
	
	@Override
	public void kill() {
		super.kill();
		
		if (emo != null) {
			emo.killAndErase();
		}
		
		for( State s : State.values()){
			remove(s);
		}
		
		if (health != null){
			health.killAndErase();
		}
	}

	private float[] shadowMatrix = new float[16];

	@Override
	protected void updateMatrix() {
		super.updateMatrix();
		Matrix.copy(matrix, shadowMatrix);
		Matrix.translate(shadowMatrix,
				(width() * (1f - shadowWidth)) / 2f,
				(height() * (1f - shadowHeight)) + shadowOffset);
		Matrix.scale(shadowMatrix, shadowWidth, shadowHeight);
	}

	@Override
	public void draw() {
		if (texture == null || (!dirty && buffer == null))
			return;

		if (renderShadow) {
			if (dirty) {
				verticesBuffer.position(0);
				verticesBuffer.put(vertices);
				if (buffer == null)
					buffer = new Vertexbuffer(verticesBuffer);
				else
					buffer.updateVertices(verticesBuffer);
				dirty = false;
			}

			NoosaScript script = script();

			texture.bind();

			script.camera(camera());

			updateMatrix();

			script.uModel.valueM4(shadowMatrix);
			script.lighting(
					0, 0, 0, am * .6f,
					0, 0, 0, aa * .6f);

			script.drawQuad(buffer);
		}

		super.draw();

	}

	@Override
	public void onComplete( Tweener tweener ) {
		if (tweener == jumpTweener) {

			if (visible && Dungeon.level.water[ch.pos] && !ch.flying) {
				GameScene.ripple( ch.pos );
			}
			if (jumpCallback != null) {
				jumpCallback.call();
			}

		} else if (tweener == motion) {

			synchronized (this) {
				isMoving = false;

				motion.killAndErase();
				motion = null;
				ch.onMotionComplete();

				notifyAll();
			}

		}
	}

	@Override
	public void onComplete( Animation anim ) {
		
		if (animCallback != null) {
			Callback executing = animCallback;
			animCallback = null;
			executing.call();
		} else {
			
			if (anim == attack) {
				
				idle();
				ch.onAttackComplete();
				
			} else if (anim == operate) {
				
				idle();
				ch.onOperateComplete();
				
			}
			
		}
	}

	private static class JumpTweener extends Tweener {

		public Visual visual;

		public PointF start;
		public PointF end;

		public float height;

		public JumpTweener( Visual visual, PointF pos, float height, float time ) {
			super( visual, time );

			this.visual = visual;
			start = visual.point();
			end = pos;

			this.height = height;
		}

		@Override
		protected void updateValues( float progress ) {
			visual.point( PointF.inter( start, end, progress ).offset( 0, -height * 4 * progress * (1 - progress) ) );
		}
	}
}
