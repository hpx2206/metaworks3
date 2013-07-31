package org.uengine.kernel.designer.web;

import org.uengine.codi.mw3.webProcessDesigner.CanvasDTO;
import org.uengine.kernel.graph.Transition;

public class TransitionView extends CanvasDTO{
	transient Transition transition;
		public Transition getTransition() {
			return transition;
		}
		public void setTransition(Transition transition) {
			this.transition = transition;
		}
}
