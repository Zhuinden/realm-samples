package io.caster.zhuinden.realmexamples

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_person.*
import java.util.*

/**
 * Created by Zhuinden on 2018.03.30..
 */
class PersonAdapter : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
    private var persons: List<Person> = Collections.emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false))

    override fun getItemCount(): Int = persons.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(persons.get(position))
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(person: Person) {
            personName.text = person.name
        }
    }

    fun updateData(persons: List<Person>) {
        this.persons = persons
        notifyDataSetChanged()
    }
}