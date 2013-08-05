/*
 * Copyright (c) 2002-2013, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.pluginwizard.business.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;


/**
 * This is the business class for the object BusinessClass
 */
public class BusinessClass
{
    // Variables declarations 
    private int _nIdFeature;
    private int _nIdBusinessClass;
    private String _strBusinessClass;
    private String _strBusinessTableName;
    private List<Attribute> _listAttributes;
    private String _strPrimaryAttributeName;
    private String _strDescriptionAttributeName;

    /**
     *
     */
    public BusinessClass(  )
    {
        _listAttributes = new ArrayList<Attribute>(  );
    }

    /**
     * Returns the nIdBusinessClass
     *
     * @return The nIdBusinessClass
     */
    public int getId(  )
    {
        return _nIdBusinessClass;
    }

    /**
     * Sets the nIdBusinessClass
     *
     * @param nIdBusinessClass The IdPlugin
     */
    public void setId( int nIdBusinessClass )
    {
        _nIdBusinessClass = nIdBusinessClass;
    }

    /**
     * Returns the IdFeature
     *
     * @return The IdFeature
     */
    public int getIdFeature(  )
    {
        return _nIdFeature;
    }

    /**
     * Sets the IdFeature
     *
     * @param nIdFeature The IdPlugin
     */
    public void setIdFeature( int nIdFeature )
    {
        _nIdFeature = nIdFeature;
    }

    /**
     * Returns the BusinessClass
     *
     * @return The BusinessClass
     */
    public String getBusinessClass(  )
    {
        return _strBusinessClass;
    }

    /**
     * Sets the BusinessClass
     *
     * @param strBusinessClass The BusinessClass
     */
    public void setBusinessClass( String strBusinessClass )
    {
        _strBusinessClass = strBusinessClass;
    }

    /**
     * Returns the BusinessTableName
     *
     * @return The BusinessTableName
     */
    public String getBusinessTableName(  )
    {
        return _strBusinessTableName;
    }

    /**
     * Sets the BusinessTableName
     *
     * @param strBusinessTableName The BusinessTableName
     */
    public void setBusinessTableName( String strBusinessTableName )
    {
        _strBusinessTableName = strBusinessTableName;
    }

    /////////////////////////////////////////////////////////////
    /**
     * Sets the list of attributes associated to business class
     *
     * @param listAttributes The collection of attributes associated to the
     * class
     */
    public void setAttributes( List<Attribute> listAttributes )
    {
        _listAttributes = listAttributes;
    }

    /**
     * Returns the collection of attributes
     *
     * @return the collection of child attributes
     */
    public List<Attribute> getAttributes(  )
    {
        return _listAttributes;
    }

    /**
     * Sets the class description and it is given by an attribute
     *
     * @param strDescriptionAttributeName The name of the attribute which the
     * description
     */
    public void setClassDescription( String strDescriptionAttributeName )
    {
        _strDescriptionAttributeName = strDescriptionAttributeName;
    }

    /**
     * Fetches the description of a business class
     *
     * @return The description of the class
     */
    public String getClassDescription(  )
    {
        return _strDescriptionAttributeName;
    }

    /**
     * Sets the primary key of the class
     *
     * @param strPrimaryAttributeName The key attribute name
     */
    public void setPrimaryKey( String strPrimaryAttributeName )
    {
        _strPrimaryAttributeName = strPrimaryAttributeName;
    }

    /**
     * Fetches the attributes which represents the identifier of the business
     * class
     *
     * @return The key
     */
    public String getPrimaryKey(  )
    {
        return _strPrimaryAttributeName;
    }

    //////////////////////////////////////////////////////////////
    // The methods below are meant to be used when generating the artifacts of the plugin

    /**
     * Fetches the primary key
     *
     * @return The name of the key
     */
    @JsonIgnore
    public String getPrimaryKeyName(  )
    {
        return getProperName( _strPrimaryAttributeName );
    }

    /**
     * Returns the BusinessClass
     *
     * @return The BusinessClass
     */
    @JsonIgnore
    public String getBusinessClassCapsFirst(  )
    {
        char[] characters = _strBusinessClass.toCharArray(  );
        characters[0] = Character.toTitleCase( characters[0] );

        return String.valueOf( characters );
    }

    /**
     * Returns the InstanceName
     *
     * @return The InstanceName
     */
    @JsonIgnore
    public String getInstanceName(  )
    {
        String strInstanceName = _strBusinessClass.substring( 0, 1 ).toLowerCase(  ) +
            _strBusinessClass.substring( 1, _strBusinessClass.length(  ) );

        return strInstanceName;
    }

    /**
     * Returns the Proper Name
     *
     * @param strSource the source
     * @return source
     */
    @JsonIgnore
    public static String getProperName( String strSource )
    {
        int nIndex = 0;
        boolean bUpper = true;
        StringBuilder strBuffer = new StringBuilder(  );

        while ( nIndex < strSource.length(  ) )
        {
            char c = strSource.charAt( nIndex );

            if ( c == '_' )
            {
                // skip by reading the next char
                nIndex++;
                bUpper = true;
            }

            if ( bUpper )
            {
                String strChar = strSource.substring( nIndex, nIndex + 1 );
                c = strChar.toUpperCase(  ).charAt( 0 );
                bUpper = false;
            }

            strBuffer.append( c );
            nIndex++;
        }

        return strBuffer.toString(  );
    }
}
