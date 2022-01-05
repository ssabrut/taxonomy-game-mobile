package com.taxon_mobile.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class ShowUserCreature {

    private int id;
    private int genus_id;
    private String name;
    private String common_name;
    private int price;
    private String description;
    private String image_path;
    private Object prerequisite_id;
    private String created_at;
    private String updated_at;
    private Genus genus;

    public static ShowUserCreature objectFromData(String str) {

        return new Gson().fromJson(str, ShowUserCreature.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGenus_id() {
        return genus_id;
    }

    public void setGenus_id(int genus_id) {
        this.genus_id = genus_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public Object getPrerequisite_id() {
        return prerequisite_id;
    }

    public void setPrerequisite_id(Object prerequisite_id) {
        this.prerequisite_id = prerequisite_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Genus getGenus() {
        return genus;
    }

    public void setGenus(Genus genus) {
        this.genus = genus;
    }

    public static class Genus {
        private int id;
        private String name;
        private int family_id;
        private Family family;

        public static Genus objectFromData(String str) {

            return new Gson().fromJson(str, Genus.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getFamily_id() {
            return family_id;
        }

        public void setFamily_id(int family_id) {
            this.family_id = family_id;
        }

        public Family getFamily() {
            return family;
        }

        public void setFamily(Family family) {
            this.family = family;
        }

        public static class Family {
            private int id;
            private String name;
            private int order_id;
            private Order order;

            public static Family objectFromData(String str) {

                return new Gson().fromJson(str, Family.class);
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public Order getOrder() {
                return order;
            }

            public void setOrder(Order order) {
                this.order = order;
            }

            public static class Order {
                private int id;
                private String name;
                private int class_id;
                @SerializedName("class")
                private Class classX;

                public static Order objectFromData(String str) {

                    return new Gson().fromJson(str, Order.class);
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getClass_id() {
                    return class_id;
                }

                public void setClass_id(int class_id) {
                    this.class_id = class_id;
                }

                public Class getClassX() {
                    return classX;
                }

                public void setClassX(Class classX) {
                    this.classX = classX;
                }

                public static class Class {
                    private int id;
                    private String name;
                    private int phylum_id;
                    private Phylum phylum;

                    public static Class objectFromData(String str) {

                        return new Gson().fromJson(str, Class.class);
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public int getPhylum_id() {
                        return phylum_id;
                    }

                    public void setPhylum_id(int phylum_id) {
                        this.phylum_id = phylum_id;
                    }

                    public Phylum getPhylum() {
                        return phylum;
                    }

                    public void setPhylum(Phylum phylum) {
                        this.phylum = phylum;
                    }

                    public static class Phylum {
                        private int id;
                        private String name;
                        private int kingdom_id;
                        private Kingdom kingdom;

                        public static Phylum objectFromData(String str) {

                            return new Gson().fromJson(str, Phylum.class);
                        }

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public int getKingdom_id() {
                            return kingdom_id;
                        }

                        public void setKingdom_id(int kingdom_id) {
                            this.kingdom_id = kingdom_id;
                        }

                        public Kingdom getKingdom() {
                            return kingdom;
                        }

                        public void setKingdom(Kingdom kingdom) {
                            this.kingdom = kingdom;
                        }

                        public static class Kingdom {
                            private int id;
                            private String name;
                            private int domain_id;
                            private Domain domain;

                            public static Kingdom objectFromData(String str) {

                                return new Gson().fromJson(str, Kingdom.class);
                            }

                            public int getId() {
                                return id;
                            }

                            public void setId(int id) {
                                this.id = id;
                            }

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public int getDomain_id() {
                                return domain_id;
                            }

                            public void setDomain_id(int domain_id) {
                                this.domain_id = domain_id;
                            }

                            public Domain getDomain() {
                                return domain;
                            }

                            public void setDomain(Domain domain) {
                                this.domain = domain;
                            }

                            public static class Domain {
                                private int id;
                                private String name;

                                public static Domain objectFromData(String str) {

                                    return new Gson().fromJson(str, Domain.class);
                                }

                                public int getId() {
                                    return id;
                                }

                                public void setId(int id) {
                                    this.id = id;
                                }

                                public String getName() {
                                    return name;
                                }

                                public void setName(String name) {
                                    this.name = name;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
