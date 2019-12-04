package engine;

import java.util.ArrayList;

public class CollisionHandler {
    private ArrayList<CollisionHandlerComponent> componentHandlers;
    private ArrayList<CollisionPair> collisionPairs;

    public CollisionHandler() {
        componentHandlers = new ArrayList<CollisionHandlerComponent>();
        collisionPairs = new ArrayList<CollisionPair>();
    }

    public void clearCollisionPairs() {
        collisionPairs.clear();
    }

    public boolean containsCollisionWith(GameObject _parent) {
        for(CollisionPair pair : collisionPairs) {
            if(pair.contains(_parent)) {
                return true;
            }
        }
        return false;
    }

    public CollisionPair getCollisionWith(GameObject _parent) {
        for(CollisionPair pair : collisionPairs) {
            if(pair.contains(_parent)) {
                return pair;
            }
        }
        return null;
    }

    public void removeComponentHandler(GameObject _parent) {
        for(int i = 0; i < componentHandlers.size(); i++) {
            if(componentHandlers.get(i).parent.equals(_parent)) {
                componentHandlers.remove(i);
                i--;
            }
        }
    }

    public CollisionHandlerComponent newCollisionHandlerComponent(GameObject _parent) {
        CollisionHandlerComponent ret = new CollisionHandlerComponent(_parent, this);
        ret.active = false;
        ret.Priority = -1;
        return ret;
    }

    public class CollisionHandlerComponent extends Component {
        public CollisionHandler collisionHandler;

        public CollisionHandlerComponent(GameObject _parent, CollisionHandler _collisionHandler) {
            super(_parent);
            collisionHandler  = _collisionHandler;
            collisionHandler.componentHandlers.add(this);
        }

        @Override
        public void logic() {
            for(CollisionHandlerComponent componentHandler : collisionHandler.componentHandlers) {
                if(!componentHandler.equals(this) &&                  // check the handler isn't this object
                   this.parent.af.getTranslateX() == componentHandler.parent.af.getTranslateX() &&  // check that row and col match (collision occurred)
                   this.parent.af.getTranslateY() == componentHandler.parent.af.getTranslateY())
                {
                    CollisionPair newCollisionPair = new CollisionPair(this.parent, componentHandler.parent);
                    if(!collisionHandler.collisionPairs.contains(newCollisionPair)) {
                        collisionHandler.collisionPairs.add(newCollisionPair);
                    }
                }
            }
        }
    }

    public class CollisionPair {
        public GameObject gameObject1, gameObject2;

        public CollisionPair(GameObject _gameObject1, GameObject _gameObject2) {
            gameObject1 = _gameObject1;
            gameObject2 = _gameObject2;
        }

        public boolean contains(GameObject _gameObject) {
            if(_gameObject.equals(gameObject1) || _gameObject.equals(gameObject2)) {
                return true;
            } else {
                return false;
            }
        }

        public boolean containsComponentClass(Class<? extends Component> _class) {
            if(gameObject1.getComponent(_class) != null || gameObject2.getComponent(_class) != null ) {
                return true;
            } else {
                return false;
            }
        }

        public Component getComponentClass(Class<? extends Component> _class) {
            if(gameObject1.getComponent(_class) != null) {
                return gameObject1.getComponent(_class);
            } else if(gameObject2.getComponent(_class) != null ) {
                return gameObject2.getComponent(_class);
            } else {
                return null;
            }
        }

        @Override
        public boolean equals(Object object) {
            if(object != null && object instanceof CollisionPair) {
                CollisionPair collisionPair = (CollisionPair)object;
                if(collisionPair.gameObject1.equals(gameObject1) && collisionPair.gameObject2.equals(gameObject2) ||
                   collisionPair.gameObject2.equals(gameObject1) && collisionPair.gameObject1.equals(gameObject2)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            return gameObject1.hashCode() + gameObject2.hashCode();
        }
    }
}


