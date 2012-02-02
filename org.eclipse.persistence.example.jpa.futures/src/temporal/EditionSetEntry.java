/*******************************************************************************
 * Copyright (c) 2011-2012 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *      dclarke - Bug 361016: Future Versions Examples
 ******************************************************************************/
package temporal;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.eclipse.persistence.annotations.VariableOneToOne;

/**
 * An EditionSet represents a proposed set of future changes that should be
 * performed together at the same effective time. This is an optimisation in the
 * model to collect all changes for a future point and simplify committing them
 * all together as the current.
 * 
 * @author dclarke
 * @since EclipseLink 2.3.1
 */
@Entity
@Table(name = "TEDITIONSET_ENTRY")
public class EditionSetEntry {

    @Id
    @GeneratedValue
    private long id;
    
    @ManyToOne
    private EditionSet editionSet;

    @VariableOneToOne(fetch=FetchType.LAZY)
    private TemporalEntity<?> edition;

    /**
     * Set of attributes that have been modified in this edition. 
     * @see TODO
     */
    @ElementCollection
    @CollectionTable(name="TEDITIONSET_ENTRY_ATTR")
    @Column(name="ATTRIBUTE")
    private Set<String> attributes = new HashSet<String>();

    private EditionSetEntry() {
        super();
    }

    public EditionSetEntry(EditionSet editionSet, TemporalEntity<?> edition) {
        this();
        this.editionSet = editionSet;
        this.edition = edition;
    }

    public long getId() {
        return id;
    }

    public TemporalEntity<?> getEdition() {
        return edition;
    }

    public EditionSet getEditionSet() {
        return editionSet;
    }

    public Set<String> getAttributes() {
        return attributes;
    }
    
}
