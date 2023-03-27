# RecyclerViewDelegate

An example of [Hannes Dorfmann AdapterDelegate](https://hannesdorfmann.com/android/adapter-delegates/) pattern impl.

The reason of this project is to show how simple is to design and write your own "AdapterDelegate" implementation.

There an example of using AdapterDelegate in simple Android project [:sample](https://github.com/MrPaperMoon/RecyclerViewDelegate/tree/main/sample/src/main/java/com/tkitm/recyclerviewdelegate/sample).

## Just be careful with few moments:
- don't put `adapter` into `viewmodel`. it's ok to have link to _viewmodel in adapter_, but adapter shouldn't have _link to viewmodel_, bcs viewmodel have different scope and alive longer
- each item should be possible to be compared - it's the _same unit_ and it _was changed_. Easy way for it, set for _same unit_ key `layout id` and for _was changed_ make delegate `data class` and use `Any::equals`
- remember, if you don't clear listeners on `onViewRecycled/onUnbindViewHolder`, it could affect on `onBindViewHolder/onBind`
- `DiffUtil` work rly slow on big diffs
- don't make Entity an RecyclerViewDelegate for reusabilyty
- RecyclerViewDelegate has access to event listener only on bind state. otherwise user may create leak by refs viewmodel as event listener and delegates in livedata
